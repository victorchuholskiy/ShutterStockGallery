package com.gmail.victorchuholskiy.shutterstockgallery.gallery

import com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadImages.LoadImagesUseCaseImpl
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_CATEGORY_ID
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_QUERY_TEXT

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class GalleryPresenter(private val view: GalleryContract.View)
	: GalleryContract.Presenter {

	private var queryText: String = DEF_QUERY_TEXT
	private var categoryId: Int = DEF_CATEGORY_ID

	init {
		view.presenter = this
	}

	override fun start() {
		loadImages(1, true)
	}

	override fun setCategoryId(id: Int) {
		categoryId = id
	}

	override fun setQueryText(query: String) {
		queryText = query
	}

	override fun loadImages(page: Int,
							clear: Boolean) {
		view.showProgress()
		LoadImagesUseCaseImpl(page, categoryId, queryText)
				.execute()
				.subscribe({ images ->
					view.showImages(images, clear)
					view.hideProgress()
				}, { error ->
					view.showError(error)
					view.hideProgress()
				})
	}
}