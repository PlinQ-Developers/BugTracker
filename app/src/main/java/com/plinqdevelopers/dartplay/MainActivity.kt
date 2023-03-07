package com.plinqdevelopers.dartplay

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.plinqdevelopers.dartplay.databinding.ActivityMainBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.models.local.BugEngineeringDTO
import com.plinqdevelopers.dartplay.models.local.BugStatus
import dagger.hilt.android.AndroidEntryPoint
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
        setContentView(view)

        setupNavigation()

        // loadSomeBugsToDB()
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
    }

    private fun loadSomeBugsToDB() {
        val random = Random()

        val bugItems = listOf(
            BugDTO(
                bugTicketID = "BT-" + String.format("%04d", random.nextInt(10000)),
                bugTitle = "App crashes when opening Settings",
                bugDescription = "When the user tries to open the app's settings screen, the app crashes unexpectedly.",
                bugClassification = BugClassification.CRITICAL,
                bugAttachments = listOf("https://example.com/image1.png", "https://example.com/image2.png"),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.UNDER_DEVELOPMENT,
                    engineeringDeveloper = "John Doe"
                ),
                bugSubmittedDate = Date().toString(),
                bugAccountID = ""
            ),
            BugDTO(
                bugTicketID = "BT-" + String.format("%04d", random.nextInt(10000)),
                bugTitle = "Incorrect calculation in checkout process",
                bugDescription = "When the user selects a certain combination of items in the checkout process, the total price is calculated incorrectly.",
                bugClassification = BugClassification.MINOR,
                bugAttachments = listOf("https://example.com/image3.png"),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.IN_TESTING,
                    engineeringDeveloper = "Jane Smith"
                ),
                bugSubmittedDate = Date().toString(),
                bugAccountID = ""
            ),
            BugDTO(
                bugTicketID = "BT-" + String.format("%04d", random.nextInt(10000)),
                bugTitle = "UI glitch on home screen",
                bugDescription = "When the user scrolls down on the home screen, the header image appears to shift slightly.",
                bugClassification = BugClassification.COSMETIC,
                bugAttachments = emptyList(),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.COMPLETED,
                    engineeringDeveloper = "Bob Johnson"
                ),
                bugSubmittedDate = Date().toString(),
                bugAccountID = ""
            ),
            BugDTO(
                bugTicketID = "BT-" + String.format("%04d", random.nextInt(10000)),
                bugTitle = "UI glitch on home screen",
                bugDescription = "When the user scrolls down on the home screen, the header image appears to shift slightly.",
                bugClassification = BugClassification.COSMETIC,
                bugAttachments = emptyList(),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.UNDER_DEVELOPMENT,
                    engineeringDeveloper = "Bob Johnson"
                ),
                bugSubmittedDate = Date().toString(),
                bugAccountID = ""
            )
        )

        lifecycleScope.launchWhenCreated {
            mainActivityViewModel.uploadBugsList(bugItems)
        }
    }

    private fun setupNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivity_cv_host) as NavHostFragment
        navController = navHostFragment.findNavController()
    }
}
