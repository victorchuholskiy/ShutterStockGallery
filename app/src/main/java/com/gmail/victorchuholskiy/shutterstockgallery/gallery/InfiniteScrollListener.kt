package com.gmail.victorchuholskiy.shutterstockgallery.gallery

/**
 * Created by viktor.chukholskiy
 * 11/07/18.
 */
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class InfiniteScrollListener(private val func: () -> Unit) : RecyclerView.OnScrollListener() {

	private var isLoading: Boolean = false

	var hasMorePages: Boolean = true
	var isRefreshing: Boolean = false

	override fun onScrolled(recyclerView: RecyclerView,
							dx: Int,
							dy: Int) {
		super.onScrolled(recyclerView, dx, dy)
		val manager = recyclerView.layoutManager as GridLayoutManager?

		val visibleItemCount = manager!!.childCount
		val totalItemCount = manager.itemCount
		val pastVisibleItems  = manager.findFirstVisibleItemPosition()

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