package com.zm.blockchain_transactions.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zm.blockchain_transactions.di.CoreDependencies
import com.zm.blockchain_transactions.profile.di.DaggerProfileComponent
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
        return binding.root
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