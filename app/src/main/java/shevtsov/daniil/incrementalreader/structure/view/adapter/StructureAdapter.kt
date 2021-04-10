package shevtsov.daniil.incrementalreader.structure.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shevtsov.daniil.incrementalreader.databinding.ItemStructureBinding

typealias StructureAdapterCallback = (action: StructureAdapterAction) -> Unit

class StructureAdapter(private val actionCallback: StructureAdapterCallback) :
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

        return ViewHolder(
            binding = itemBinding,
            actionCallback = actionCallback
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(item = getItem(position))


    class ViewHolder(
        private val binding: ItemStructureBinding,
        private val actionCallback: StructureAdapterCallback
    ) : RecyclerView.ViewHolder(binding.root) {

        private var itemId: Long? = null

        init {
            binding.root.setOnClickListener {
                itemId?.let {
                    actionCallback.invoke(StructureAdapterAction.ItemSelected(itemId = it))
                }
            }

            binding.structureItemEditButton.setOnClickListener {
                itemId?.let {
                    actionCallback.invoke(StructureAdapterAction.EditButtonClicked(itemId = it))
                }
            }
        }

        fun bind(item: StructureInformationItem) {
            itemId = item.id

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