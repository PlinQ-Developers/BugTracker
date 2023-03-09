package com.plinqdevelopers.dartplay.ui.ui_create_report // ktlint-disable package-name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.databinding.FragmentCreateBugBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.models.local.BugEngineeringDTO
import com.plinqdevelopers.dartplay.models.local.BugStatus
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CreateBugFragment : Fragment() {
    private var _binding: FragmentCreateBugBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Error loading view: create")

    private val createBugViewModel: CreateBugViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBugBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDropdownMenu()
        initButtons()
    }

    private fun initDropdownMenu() {
        val dropdownAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_list_item,
            resources.getStringArray(R.array.bugTypeClassification)
        )

        binding.createBugFragmentTetBugClass.setAdapter(dropdownAdapter)
    }

    private fun initButtons() {
        binding.createBugFragmentIvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.createBugFragmentBtnSubmitReport.setOnClickListener {
            if (
                isInputFieldValid(binding.createBugFragmentTilBugName, "Bug Title") &&
                isInputFieldValid(binding.createBugFragmentTilBugClass, "Bug Classification") &&
                isInputFieldValid(binding.createBugFragmentTilDescriptionName, "Bug Description")
            ) {
                val bugTitleInput = binding.createBugFragmentTetBugName.text.toString()
                val bugDescriptionInput = binding.createBugFragmentTetDescriptionName.text.toString()
                val bugClassificationInput = binding.createBugFragmentTetBugClass.text.toString()

                Timber.tag("t-logs").d("title: $bugTitleInput \nClassification $bugClassificationInput")

                saveBugToDatabase(
                    bugTitle = bugTitleInput,
                    bugDescription = bugDescriptionInput,
                    bugClassification = bugClassificationInput
                )
            } else {
                val errorBar = Snackbar.make(
                    requireView(),
                    "We could not validate some field forms. Please review and try again!",
                    Snackbar.LENGTH_INDEFINITE
                )

                errorBar.setAction("Okay") {
                    errorBar.dismiss()
                }.show()
            }
        }
    }

    private fun isInputFieldValid(
        editTextLayout: TextInputLayout,
        fieldName: String
    ): Boolean {
        val inputText = editTextLayout.editText?.text.toString()
        return if (inputText.isEmpty()) {
            editTextLayout.error = "$fieldName Should not be empty or is not correct"
            false
        } else {
            editTextLayout.error = ""
            true
        }
    }

    private fun saveBugToDatabase(
        bugTitle: String,
        bugDescription: String,
        bugClassification: String
    ) {
        val newBugItem = BugDTO(
            bugAccountID = generateAccountID(),
            bugAttachments = generateImageList(),
            bugTitle = bugTitle,
            bugDescription = bugDescription,
            bugSubmittedDate = getTodayLocalDate(),
            bugClassification = classifyBugItem(
                selectedType = bugClassification
            ),
            bugTicketID = generateTicketNumber(),
            bugEngineering = BugEngineeringDTO(
                engineeringAcceptance = "Unassigned",
                engineeringStatus = BugStatus.PENDING,
                engineeringDeveloper = "Unassigned"
            )
        )

        lifecycleScope.launchWhenCreated {
            createBugViewModel.saveUploadedBug(
                bugItem = newBugItem
            )
        }

        showSuccessSnackBar()
        binding.apply {
            createBugFragmentTetBugName.text?.clear()
            createBugFragmentTetDescriptionName.text?.clear()
            createBugFragmentTetBugClass.text?.clear()
        }
    }

    private fun getTodayLocalDate(): String {
        val formatter = SimpleDateFormat("MM-dd-yyyy")
        val date = formatter.format(Date())
        return date.toString()
    }

    private fun generateTicketNumber(): String {
        val random = Random()
        return "BT-" + String.format("%04d", random.nextInt(10000))
    }

    private fun generateAccountID(): String {
        val random = Random()
        return "ACC-" + String.format("%04d", random.nextInt(10000))
    }

    private fun generateImageList(): List<String> {
        return listOf(
            "https://ntrack.com/img/android_bug6.png",
            "https://ntrack.com/img/android_bug7.png",
            "https://ntrack.com/img/android_bug8.png"
        )
    }

    private fun classifyBugItem(selectedType: String): BugClassification {
        return when (selectedType) {
            "CRITICAL BUGS" -> BugClassification.CRITICAL
            "MINOR BUGS" -> BugClassification.MINOR
            "COSMETIC BUGS" -> BugClassification.COSMETIC
            "OTHER BUGS" -> BugClassification.OTHER
            else -> BugClassification.NOT_ASSIGNED
        }
    }

    private fun showSuccessSnackBar() {
        val successBar = Snackbar.make(
            requireView(),
            "Your bug report has been successfully added to our database. You can monitor it from your error logs dashboard. Thanks",
            Snackbar.LENGTH_INDEFINITE
        )
        successBar.setAction("Sure") {
            successBar.dismiss()
        }
        successBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
