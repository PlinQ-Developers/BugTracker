package com.plinqdevelopers.dartplay.ui.ui_report_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.adapters.BugAttachmentsAdapter
import com.plinqdevelopers.dartplay.databinding.FragmentBugReportDetailsBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BugReportDetailsFragment : Fragment(), BugAttachmentsAdapter.OnImageItemClickedListener {
    private var _binding: FragmentBugReportDetailsBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Error loading view: details")

    private val args: BugReportDetailsFragmentArgs by navArgs()
    private val attachmentsAdapter: BugAttachmentsAdapter = BugAttachmentsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBugReportDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAttachmentsList()
        initButtons()
        initView()
    }

    private fun initButtons() {
        binding.bugReportDetailsFragmentIvMore.setOnClickListener {
            // Todo: add dialog
        }

        binding.bugReportDetailsFragmentIvBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        try {
            val bugItemDTO: BugDTO = args.bugItemDTO
            attachmentsAdapter.submitList(bugItemDTO.bugAttachments)

            binding.apply {
                bugItemTvBugName.text = bugItemDTO.bugTitle
                bugItemTvDescription.text = bugItemDTO.bugDescription
                bugReportDetailsFragmentTvTicketNumber.text = bugItemDTO.bugTicketID
                bugItemTvBugReportedDate.text = bugItemDTO.bugSubmittedDate
                bugItemTvStatus.text = bugItemDTO.bugEngineering.engineeringStatus.toString()
                bugItemTvReceivedDate.text = bugItemDTO.bugEngineering.engineeringAcceptance
                bugItemTvStatusName.text = bugItemDTO.bugEngineering.engineeringStatus.toString()
                bugItemTvDevName.text = bugItemDTO.bugEngineering.engineeringDeveloper

                when (bugItemDTO.bugClassification) {
                    BugClassification.CRITICAL -> bugItemIvClassIcon.load(R.drawable.ic_bug)
                    BugClassification.OTHER -> bugItemIvClassIcon.load(R.drawable.ic_bug_other)
                    BugClassification.MINOR -> bugItemIvClassIcon.load(R.drawable.ic_bug_minor)
                    BugClassification.COSMETIC -> bugItemIvClassIcon.load(R.drawable.ic_bug_cosmetic)
                    BugClassification.NOT_ASSIGNED -> bugItemIvClassIcon.load(R.drawable.ic_close_window)
                }
            }
        } catch (exception: IllegalArgumentException) {
            val exceptionSnackBar = Snackbar.make(
                requireView(),
                "We ran into an error loading the details. Please try and refresh the page.",
                Snackbar.LENGTH_INDEFINITE
            )

            exceptionSnackBar.setAction("Ok") {
                exceptionSnackBar.dismiss()
            }

            exceptionSnackBar.show()
        }
    }

    private fun initAttachmentsList() {
        binding.bugItemsAttachmentList.apply {
            adapter = attachmentsAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )
            hasFixedSize()
        }
    }

    override fun loadImageDialogWindow(imageURL: String) {
        // Todo: add dialog fragment here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
