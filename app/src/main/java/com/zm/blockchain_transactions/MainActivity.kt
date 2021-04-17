package com.zm.blockchain_transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.zm.blockchain_transactions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setupNavigation()
	}

	private fun setupNavigation() {
		val navHost = supportFragmentManager.findFragmentById(R.id.container) as DynamicNavHostFragment
		val navController = navHost.navController
		binding.bottomMenu.setupWithNavController(navController)
	}
}