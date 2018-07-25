package com.gmail.victorchuholskiy.shutterstockgallery.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.adapters.ImagesAdapter
import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class GalleryFragment : Fragment(), GalleryContract.View {

	override lateinit var presenter: GalleryContract.Presenter

	private lateinit var rvImages: RecyclerView
	private lateinit var srlRefresh: SwipeRefreshLayout

	private val imagesAdapter = ImagesAdapter(ArrayList(), listener = {})
	private val infiniteScrollListener = InfiniteScrollListener(func = { presenter.loadImages(imagesAdapter.page + 1) })

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_gallery, container, false)

		val imagesLayoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)

		with(view) {
			rvImages = findViewById<RecyclerView>(R.id.rv_images).apply {
				setHasFixedSize(true)
				layoutManager = imagesLayoutManager
				adapter = imagesAdapter
				//addOnScrollListener(infiniteScrollListener)
			}

			srlRefresh = findViewById<SwipeRefreshLayout>(R.id.srl_refresh).apply {
				setColorSchemeColors(
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorPrimary),
						android.support.v4.content.ContextCompat.getColor(requireContext(), R.color.colorAccent)
				)
				setOnRefreshListener { presenter.loadImages(1, clear = true) }
			}
		}
		return view
	}

	override fun onResume() {
		super.onResume()
		presenter.start()
	}

	override fun showImages(images: List<ImageModel>, clear: Boolean) {
		if (clear) imagesAdapter.setData(images) else imagesAdapter.addData(images)
		infiniteScrollListener.isRefreshing = false
	}

	override fun showProgress() {
		srlRefresh.isRefreshing = true
	}

	override fun hideProgress() {
		srlRefresh.isRefreshing = false
	}

	override fun showError(msg: String) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
	}

	companion object {
		fun newInstance() = GalleryFragment()
	}
}