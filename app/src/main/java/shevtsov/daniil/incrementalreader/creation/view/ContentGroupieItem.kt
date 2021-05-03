package shevtsov.daniil.incrementalreader.creation.view

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.databinding.ItemContentPartBinding

data class ContentGroupieItem(
    val contentPartId: Long,
    val contentPart: String,
) : BindableItem<ItemContentPartBinding>() {

    override fun getId(): Long = contentPartId

    override fun bind(
        viewBinding: ItemContentPartBinding,
        position: Int
    ) {
        with(viewBinding) {
            contentPartEditText.setText(contentPart)
        }
    }

    override fun getLayout(): Int = R.layout.item_content_part

    override fun initializeViewBinding(
        view: View
    ) = ItemContentPartBinding.bind(view)

}