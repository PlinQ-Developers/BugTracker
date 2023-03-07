package com.plinqdevelopers.dartplay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.databinding.BugListItemBinding
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO

class BugListAdapter(
    private val clickListener: BugItemClickedListener
) : ListAdapter<BugDTO, BugListAdapter.BugListAdapterViewHolder>(BugListComparator()) {
    inner class BugListAdapterViewHolder(
        private val binding: BugListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    val bugItem = getItem(itemPosition)
                    clickListener.onBugItemClicked(bugDTO = bugItem)
                }
            }
        }

        fun bindView(bugDTO: BugDTO) {
            binding.apply {
                bugListItemTvBugNumber.text = bugDTO.bugTicketID
                bugListItemTvBugName.text = bugDTO.bugTitle
                bugListItemTvBugDate.text = bugDTO.bugSubmittedDate

                when (bugDTO.bugClassification) {
                    BugClassification.COSMETIC -> bugListItemIvBugIcon.load(R.drawable.ic_bug_cosmetic)
                    BugClassification.CRITICAL -> bugListItemIvBugIcon.load(R.drawable.ic_bug)
                    BugClassification.MINOR -> bugListItemIvBugIcon.load(R.drawable.ic_bug_minor)
                    BugClassification.OTHER -> bugListItemIvBugIcon.load(R.drawable.ic_bug_other)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BugListAdapterViewHolder {
        val bugListItemBinding = BugListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BugListAdapterViewHolder(binding = bugListItemBinding)
    }

    override fun onBindViewHolder(
        holder: BugListAdapterViewHolder,
        position: Int
    ) {
        val bugDTOItem = getItem(position)
        holder.bindView(bugDTO = bugDTOItem)
    }

    interface BugItemClickedListener {
        fun onBugItemClicked(bugDTO: BugDTO)
    }

    class BugListComparator : DiffUtil.ItemCallback<BugDTO>() {
        override fun areItemsTheSame(
            oldItem: BugDTO,
            newItem: BugDTO
        ): Boolean {
            return oldItem.bugID == newItem.bugID
        }

        override fun areContentsTheSame(
            oldItem: BugDTO,
            newItem: BugDTO
        ): Boolean {
            return oldItem == newItem
        }
    }
}
