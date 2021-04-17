package com.zm.blockchain_transactions.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zm.blockchain_transactions.databinding.ActivityLoginBinding
import com.zm.domain.model.SessionData
import com.zm.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            loginViewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }
        loginViewModel.loginResult.observe(this, {
            when (it) {
                is Resource.Failure -> {
                    showLoginFailed(it.error?.message ?: "Please check your Internet connection")
                    binding.loading.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Success<SessionData> -> {
                    binding.loading.visibility = View.GONE
                }
            }
        })
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}