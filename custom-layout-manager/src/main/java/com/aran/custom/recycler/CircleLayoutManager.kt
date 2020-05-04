package com.aran.custom.recycler

import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView.*
import kotlin.math.floor

class CircleLayoutManager : LayoutManager() {

    private var horizontalScrollOffset = 0

    override fun generateDefaultLayoutParams(): LayoutParams =
        LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    override fun canScrollHorizontally(): Boolean = true

    override fun scrollHorizontallyBy(dx: Int, recycler: Recycler, state: State): Int {
        horizontalScrollOffset += dx
        fill(recycler)
        return dx
    }

    override fun onLayoutChildren(recycler: Recycler, state: State) {
        fill(recycler)
    }

    private fun fill(recycler: Recycler) {
        detachAndScrapAttachedViews(recycler)

        val viewWidth = 120
        val firstVisibleItemPosition = floor(horizontalScrollOffset.toDouble() / viewWidth).toInt()
        val lastVisibleItemPosition = (horizontalScrollOffset + width) / viewWidth
        for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
            var recyclerIndex = i % itemCount
            if (recyclerIndex < 0) {
                recyclerIndex += itemCount
            }
            val view = recycler.getViewForPosition(recyclerIndex)
            addView(view)

            val top = 0
            val bottom = top + viewWidth
            val left = i * viewWidth - horizontalScrollOffset
            val right = left + viewWidth

            measureChild(view, viewWidth, viewWidth)

            layoutDecorated(view, left, top, right, bottom)
        }

        val scrapListCopy = recycler.scrapList.toList()
        scrapListCopy.forEach {
            recycler.recycleView(it.itemView)
        }
    }
}