package com.plinqdevelopers.dartplay

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.plinqdevelopers.dartplay.data.network.Resources
import com.plinqdevelopers.dartplay.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        installSplashScreen()
        setupNavigation()
        setContentView(view)

        downloadAppData()
    }

    private fun downloadAppData() {
        val applicationDataResource = mainActivityViewModel.downloadApplicationData()
        applicationDataResource.observe(this@MainActivity) { resValue ->
            when (resValue) {
                is Resources.Loading -> {
                    binding.mainActivityPbLoadingStatus.visibility = View.VISIBLE
                }
                is Resources.Success -> {
                    binding.mainActivityPbLoadingStatus.visibility = View.GONE
                    //Everything is okay
                }
                is Resources.Error -> {
                    binding.mainActivityPbLoadingStatus.visibility = View.GONE
                    showToast(
                        message = "Error loading data from cloud"
                    )
                }
            }
        }
    }

    private fun showToast(
        message: String
    ) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
    }

    private fun setupNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivity_cv_host) as NavHostFragment
        navController = navHostFragment.findNavController()
    }
}
