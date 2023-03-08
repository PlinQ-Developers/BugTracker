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
import java.text.SimpleDateFormat
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

        //loadSomeBugsToDB()
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
                bugTitle = "Sed ac enim ut sapien molestie finibus. Vestibulum mollis ipsum est, eget fermentum ante faucibus at.",
                bugDescription = "Nullam finibus mi massa, eu egestas elit pellentesque at. Suspendisse commodo euismod lorem non luctus. Aenean quis nisi a risus aliquam bibendum. Nullam ut convallis mauris, ut euismod ante. Fusce tincidunt nunc lacus, eu eleifend sapien convallis vulputate. Pellentesque sagittis est vel pretium sollicitudin. \n\nProin imperdiet, nibh quis lacinia pulvinar, nisl sapien ultrices nisi, vel mollis velit eros ut tortor. Suspendisse consequat augue eu dui tincidunt porttitor. Integer velit odio, faucibus vitae sodales nec, feugiat nec libero. Duis vehicula dui dolor, ut finibus felis iaculis eget. Sed eu mauris vestibulum, blandit nunc ut, vulputate arcu. Maecenas hendrerit massa non commodo dapibus.",
                bugClassification = BugClassification.OTHER,
                bugAttachments = listOf("https://ntrack.com/img/android_bug1.png", "https://ntrack.com/img/android_bug1.png"),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.UNDER_DEVELOPMENT,
                    engineeringDeveloper = "John Doe"
                ),
                bugSubmittedDate = getLocalDate(),
                bugAccountID = ""
            ),
            BugDTO(
                bugTicketID = "BT-" + String.format("%04d", random.nextInt(10000)),
                bugTitle = "Sed posuere, diam id feugiat accumsan, leo ante volutpat purus, sed malesuada leo eros sit amet massa.",
                bugDescription = "Nullam finibus mi massa, eu egestas elit pellentesque at. Suspendisse commodo euismod lorem non luctus. Aenean quis nisi a risus aliquam bibendum. Nullam ut convallis mauris, ut euismod ante. Fusce tincidunt nunc lacus, eu eleifend sapien convallis vulputate. Pellentesque sagittis est vel pretium sollicitudin. \n\nProin imperdiet, nibh quis lacinia pulvinar, nisl sapien ultrices nisi, vel mollis velit eros ut tortor. Suspendisse consequat augue eu dui tincidunt porttitor. Integer velit odio, faucibus vitae sodales nec, feugiat nec libero. Duis vehicula dui dolor, ut finibus felis iaculis eget. Sed eu mauris vestibulum, blandit nunc ut, vulputate arcu. Maecenas hendrerit massa non commodo dapibus.",
                bugClassification = BugClassification.OTHER,
                bugAttachments = listOf("https://ntrack.com/img/android_bug6.png", "https://ntrack.com/img/android_bug4.png", "https://ntrack.com/img/android_bug6.png"),
                bugEngineering = BugEngineeringDTO(
                    engineeringAcceptance = Date().toString(),
                    engineeringStatus = BugStatus.UNDER_DEVELOPMENT,
                    engineeringDeveloper = "Samwel John"
                ),
                bugSubmittedDate = getLocalDate(),
                bugAccountID = ""
            )
        )

        lifecycleScope.launchWhenCreated {
            mainActivityViewModel.uploadBugsList(bugItems)
        }
    }

    private fun getLocalDate(): String {
        val formatter = SimpleDateFormat("MM-dd-yyyy")
        val date = formatter.format(Date())
        return date.toString()
    }

    private fun setupNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivity_cv_host) as NavHostFragment
        navController = navHostFragment.findNavController()
    }
}
