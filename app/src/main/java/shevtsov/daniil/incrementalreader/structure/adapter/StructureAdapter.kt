package shevtsov.daniil.incrementalreader.structure.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shevtsov.daniil.incrementalreader.databinding.ItemStructureBinding
import shevtsov.daniil.incrementalreader.structure.StructureInformationItem

class StructureAdapter :
    ListAdapter<StructureInformationItem, StructureAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding = ItemStructureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding = itemBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(item = getItem(position))


    class ViewHolder(
        private val binding: ItemStructureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StructureInformationItem) {
            with(binding) {
                structureItemTitleTextView.text = item.title
            }
        }

    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<StructureInformationItem>() {
        override fun areItemsTheSame(
            oldItem: StructureInformationItem,
            newItem: StructureInformationItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: StructureInformationItem,
            newItem: StructureInformationItem
        ): Boolean = oldItem == newItem
    }
}