package com.plinqdevelopers.dartplay.ui.ui_dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.adapters.BugListAdapter
import com.plinqdevelopers.dartplay.databinding.FragmentBugDashboardBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BugDashboardFragment : Fragment(), BugListAdapter.BugItemClickedListener {
    private var _binding: FragmentBugDashboardBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("")

    private val bugDashboardViewModel: BugDashboardViewModel by viewModels()
    private val bugListAdapter: BugListAdapter = BugListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBugDashboardBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initListView()

        loadBugItemsList()
    }

    private fun initButtons() {
        binding.bugDashboardFragmentFabNewBugBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_bugDashboardFragment_to_createBugFragment
            )
        }

        binding.bugDashboardFragmentRlBugListHeader.setOnClickListener {
            findNavController().navigate(
                R.id.action_bugDashboardFragment_to_bugReportsFragment
            )
        }
    }

    private fun initListView() {
        binding.bugDashboardFragmentRvTopBugList.apply {
            adapter = bugListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }
    private fun loadBugItemsList() {
        bugDashboardViewModel.recentBugItems.observe(requireActivity()) { bugsList ->
            bugListAdapter.submitList(bugsList.take(3))
            computeBugAnalytics(bugsList)
        }
    }

    private fun computeBugAnalytics(bugsList: List<BugDTO>) {
        val bugTypesCount = bugsList.groupingBy {
            it.bugClassification
        }.eachCount()

        val criticalBugsPercentile = (bugTypesCount[BugClassification.CRITICAL] ?: 0).toFloat() / bugsList.size * 100
        val minorBugsPercentile = (bugTypesCount[BugClassification.MINOR] ?: 0).toFloat() / bugsList.size * 100
        val cosmeticBugsPercentile = (bugTypesCount[BugClassification.COSMETIC] ?: 0).toFloat() / bugsList.size * 100
        val otherBugsPercentile = (bugTypesCount[BugClassification.OTHER] ?: 0).toFloat() / bugsList.size * 100

        binding.apply {
            bugDashboardFragmentPbCriticalBugsProgress.progress = criticalBugsPercentile.toInt()
            bugDashboardFragmentTvCriticalBugValue.text = "$criticalBugsPercentile%"

            bugDashboardFragmentPbMinorBugsProgress.progress = minorBugsPercentile.toInt()
            bugDashboardFragmentTvMinorBugValue.text = "$minorBugsPercentile%"

            bugDashboardFragmentPbCosmeticBugsProgress.progress = cosmeticBugsPercentile.toInt()
            bugDashboardFragmentTvCosmeticBugValue.text = "$cosmeticBugsPercentile%"

            bugDashboardFragmentPbOtherBugsProgress.progress = otherBugsPercentile.toInt()
            bugDashboardFragmentTvOtherBugValue.text = "$otherBugsPercentile%"
        }
    }

    override fun onBugItemClicked(bugDTO: BugDTO) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
