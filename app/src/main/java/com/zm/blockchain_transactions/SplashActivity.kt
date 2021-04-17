package com.zm.blockchain_transactions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zm.blockchain_transactions.ui.login.LoginActivity
import com.zm.domain.repository.SessionRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var sessionRepository: SessionRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(IO).launch {
            navigate()
        }
    }

    private suspend fun navigate() {
        val intent = if (sessionRepository.isLoggedIn()) {
            Intent(this@SplashActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else Intent(this@SplashActivity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        withContext(Main) {
            startIntent(intent)
        }

    }

    private fun startIntent(intent: Intent) {
        startActivity(intent)
    }
}