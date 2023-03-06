package com.plinqdevelopers.dartplay.ui.ui_dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.databinding.FragmentBugDashboardBinding

class BugDashboardFragment : Fragment() {
    private var _binding: FragmentBugDashboardBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("")

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
