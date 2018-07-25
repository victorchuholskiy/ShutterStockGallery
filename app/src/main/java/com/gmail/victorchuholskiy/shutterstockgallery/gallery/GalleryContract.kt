package com.gmail.victorchuholskiy.shutterstockgallery.gallery

import com.gmail.victorchuholskiy.shutterstockgallery.BasePresenter
import com.gmail.victorchuholskiy.shutterstockgallery.BaseView
import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel

/**
 * Created by viktor.chukholskiy
 * 25/07/18.

 * This specifies the contract between the view and the presenter.
 */
interface GalleryContract {

	interface View : BaseView<Presenter> {

		fun showImages(images: List<ImageModel>,
					   clear: Boolean = false)

		fun showProgress()

		fun hideProgress()

		fun showError(exception: Throwable?)
	}

	interface Presenter : BasePresenter {

		fun setCategoryId(id: Int)

		fun setQueryText(query: String)

		fun loadImages(page: Int,
					   clear: Boolean = false)
	}
}