package com.zm.blockchain_transactions.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zm.blockchain_transactions.MainActivity
import com.zm.blockchain_transactions.SplashActivity
import com.zm.blockchain_transactions.di.CoreDependencies
import com.zm.blockchain_transactions.profile.di.DaggerProfileComponent
import com.zm.domain.model.Profile
import com.zm.domain.util.Resource
import com.zm.profile.databinding.ProfileFragmentBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
    }
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            ProfileFragmentBinding.inflate(inflater, container, false)
        setupViews()
        setupObservers()
        return binding.root
    }

    private fun setupViews() {
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun setupObservers() {
        viewModel.profile.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                    showError(it.error)
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    bindProfile(it.data)
                }
            }
        }

        viewModel.logout.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                    showError(it.error)
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    logout()
                }
            }
        }
        viewModel.getProfileData()
    }

    private fun logout() {
        val intent = Intent(requireContext(), SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        requireActivity().startActivity(intent)
    }

    private fun bindProfile(profile: Profile?) {
        profile?.let {
            binding.accountEmail.text = profile.email
        }
    }

    private fun showError(error: Throwable?) {
        Toast.makeText(requireContext(), error?.message?:"Check your internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    private fun inject() {
        DaggerProfileComponent
            .builder()
            .context(requireContext())
            .dependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    CoreDependencies::class.java
                )
            ).build()
            .inject(this)
    }
}