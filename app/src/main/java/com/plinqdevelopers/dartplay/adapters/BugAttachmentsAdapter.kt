package com.plinqdevelopers.dartplay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.plinqdevelopers.dartplay.R
import com.plinqdevelopers.dartplay.databinding.ItemAttachmentsBinding

class BugAttachmentsAdapter(
    private val imageListener: OnImageItemClickedListener
) : ListAdapter<String, BugAttachmentsAdapter.BugAttachmentsAdapterViewHolder>(BugAttachmentsComparator()) {
    inner class BugAttachmentsAdapterViewHolder(
        private val binding: ItemAttachmentsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val attachmentPosition = adapterPosition
                if (attachmentPosition != RecyclerView.NO_POSITION) {
                    val attachmentImage = getItem(attachmentPosition)
                    imageListener.loadImageDialogWindow(
                        imageURL = attachmentImage
                    )
                }
            }
        }

        fun bindAttachmentItem(imageURL: String) {
            binding.apply {
                root.load(imageURL) {
                    crossfade(true)
                    placeholder(R.drawable.ic_placeholder)
                    error(R.drawable.ic_placeholder)
                    transformations(RoundedCornersTransformation(4F))
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BugAttachmentsAdapterViewHolder {
        return BugAttachmentsAdapterViewHolder(
            ItemAttachmentsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BugAttachmentsAdapterViewHolder,
        position: Int
    ) {
        val imageItemURL = getItem(position)
        holder.bindAttachmentItem(
            imageURL = imageItemURL
        )
    }

    interface OnImageItemClickedListener {
        fun loadImageDialogWindow(imageURL: String)
    }

    class BugAttachmentsComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
