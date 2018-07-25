package com.gmail.victorchuholskiy.shutterstockgallery.gallery

/**
 * Created by viktor.chukholskiy
 * 11/07/18.
 */
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

class InfiniteScrollListener(
		private val func: () -> Unit) : RecyclerView.OnScrollListener() {

	private var isLoading: Boolean = false
	private var pastVisibleItems: Int = 0

	var hasMorePages: Boolean = true
	var isRefreshing: Boolean = false

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		super.onScrolled(recyclerView, dx, dy)
		val manager = recyclerView.layoutManager as StaggeredGridLayoutManager?

		val visibleItemCount = manager!!.childCount
		val totalItemCount = manager.itemCount
		val firstVisibleItems = manager.findFirstVisibleItemPositions(null)
		if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
			pastVisibleItems = firstVisibleItems[0]
		}

		if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
			isLoading = true
			if (hasMorePages && !isRefreshing) {
				isRefreshing = true
				func()
			}
		} else {
			isLoading = false
		}
	}
}