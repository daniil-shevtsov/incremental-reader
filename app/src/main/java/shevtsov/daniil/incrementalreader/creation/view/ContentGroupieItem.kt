package shevtsov.daniil.incrementalreader.creation.view

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.xwray.groupie.viewbinding.BindableItem
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.databinding.ItemContentPartBinding

data class ContentGroupieItem(
    val contentPartId: Long,
    val contentPart: String,
    val onTextChanged: (String) -> Unit,
    val onCreateChunk: (String) -> Unit,
    val onCreateCloze: (String) -> Unit,
) : BindableItem<ItemContentPartBinding>() {

    override fun getId(): Long = contentPartId

    override fun bind(
        viewBinding: ItemContentPartBinding,
        position: Int
    ) {
        with(viewBinding) {
            contentPartEditText.setText(contentPart)
            contentPartEditText.setListeners(
                textEnteredAction = onTextChanged,
            )
            contentPartEditText.apply {
                customSelectionActionModeCallback =
                    object : ActionMode.Callback {
                        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {

                            mode?.menuInflater?.inflate(R.menu.creation_share_menu, menu)
                            return true
                        }

                        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                            return false
                        }

                        override fun onActionItemClicked(
                            mode: ActionMode?,
                            item: MenuItem?
                        ): Boolean {
                            return when (item?.itemId) {
                                R.id.item_create_chunk -> {
                                    onCreateChunk.invoke(getSelectedText())
                                    true
                                }
                                R.id.item_create_cloze -> {
                                    onCreateCloze.invoke(getSelectedText())
                                    true
                                }
                                else -> false
                            }
                        }

                        override fun onDestroyActionMode(mode: ActionMode?) {

                        }
                    }
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_content_part

    override fun initializeViewBinding(
        view: View
    ) = ItemContentPartBinding.bind(view)

    private fun EditText.getSelectedText(): String {
        val startSelection = selectionStart
        val endSelection = selectionEnd

        return text.toString().substring(startSelection, endSelection)
    }

    private fun EditText.setListeners(
        textEnteredAction: (text: String) -> Unit
    ) {
        doAfterTextChanged { editable ->
            textEnteredAction.invoke(editable.toString())
        }
    }

}