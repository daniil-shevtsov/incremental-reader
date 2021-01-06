package shevtsov.daniil.incrementalreader.structure.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spaceSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val topSpace = when {
            parent.getChildAdapterPosition(view) != 0 -> spaceSize
            else -> outRect.top
        }

        with(outRect) {
            top = topSpace
        }
    }
}