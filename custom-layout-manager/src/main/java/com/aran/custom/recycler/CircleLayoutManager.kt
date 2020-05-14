package com.aran.custom.recycler

import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView.*
import kotlin.math.PI
import kotlin.math.floor
import kotlin.math.sin

class CircleLayoutManager : LayoutManager() {

    private val viewWidth = 120
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

        println("view item size = ${recycler.getViewForPosition(2).width}")

        val firstVisibleItemPosition = floor(horizontalScrollOffset.toDouble() / viewWidth).toInt()
        val lastVisibleItemPosition = (horizontalScrollOffset + width) / viewWidth
        for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
            var recyclerIndex = i % itemCount
            if (recyclerIndex < 0) {
                recyclerIndex += itemCount
            }
            val view = recycler.getViewForPosition(recyclerIndex)
            addView(view)

            val left = i * viewWidth - horizontalScrollOffset
            val right = left + viewWidth
            val top = getTopOffset(left.toDouble() + (viewWidth.toDouble() / 2))
            val bottom = top + viewWidth

            measureChild(view, viewWidth, viewWidth)

            layoutDecorated(view, left, top, right, bottom)
        }

        val scrapListCopy = recycler.scrapList.toList()
        scrapListCopy.forEach {
            recycler.recycleView(it.itemView)
        }
    }

    private fun getTopOffset(viewCenterX: Double): Int {
        val r = width / 2
        val alpha = (viewCenterX / width) * PI
        val y =  r - (r * sin(alpha))
        return y.toInt() - (viewWidth / 2)
    }
}