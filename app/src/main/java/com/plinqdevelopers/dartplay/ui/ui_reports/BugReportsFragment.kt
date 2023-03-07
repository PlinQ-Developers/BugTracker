package com.plinqdevelopers.dartplay.ui.ui_reports // ktlint-disable package-name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.adapters.BugListAdapter
import com.plinqdevelopers.dartplay.databinding.FragmentBugReportsBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.models.local.BugEngineeringDTO
import com.plinqdevelopers.dartplay.models.local.BugStatus
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BugReportsFragment : Fragment(), BugListAdapter.BugItemClickedListener {
    private var _binding: FragmentBugReportsBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("")

    private val bugListAdapter: BugListAdapter = BugListAdapter(this)
    private val reportsViewModel: BugReportsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBugReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initListView()
        loadAllBugs()
    }

    private fun initButtons() {
        binding.bugReportsFragmentTlBugTabs.apply {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    loadBugsBySelectedCategory(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    // Do something when the tab is unselected
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    // Do something when the tab is reselected
                }
            })
        }
    }

    private fun loadBugsBySelectedCategory(category: Int) {
        when(category) {
            0 -> loadAllBugs()
            1 -> loadBugsByCategory(classification = BugClassification.CRITICAL)
            2 -> loadBugsByCategory(classification = BugClassification.MINOR)
            3 -> loadBugsByCategory(classification = BugClassification.COSMETIC)
            4 -> loadBugsByCategory(classification = BugClassification.OTHER)
        }
    }

    private fun initListView() {
        binding.bugReportsFragmentRvBugsList.apply {
            adapter = bugListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    private fun loadAllBugs() {
        reportsViewModel.bugItemsList.observe(requireActivity()) {
            bugListAdapter.submitList(it)
        }
    }

    private fun loadBugsByCategory(classification: BugClassification) {
        reportsViewModel.filterListByClassification(classification).observe(requireActivity()) {
            bugListAdapter.submitList(it)
        }
    }

    override fun onBugItemClicked(bugDTO: BugDTO) {
        findNavController().navigate(
            R.id.action_bugReportsFragment_to_bugReportDetailsFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
