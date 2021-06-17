package shevtsov.daniil.incrementalreader.learning.view

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import shevtsov.daniil.incrementalreader.R
import shevtsov.daniil.incrementalreader.databinding.ItemLearningScoreBinding

data class LearningScoreGroupieItem(
    val scoreId: Long,
    val value: String,
) : BindableItem<ItemLearningScoreBinding>() {

    override fun getId(): Long = scoreId

    override fun bind(
        viewBinding: ItemLearningScoreBinding,
        position: Int
    ) {
        with(viewBinding) {
            scoreTextView.text = value
        }
    }

    override fun getLayout(): Int = R.layout.item_learning_score

    override fun initializeViewBinding(
        view: View
    ) = ItemLearningScoreBinding.bind(view)


}