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

		fun showImages(images: List<ImageModel>, clear: Boolean = false)

		fun showProgress()

		fun hideProgress()

		fun showError(msg: String)
	}

	interface Presenter : BasePresenter {

		fun loadImages(page: Int, category: Int = -1, search: String = "", clear: Boolean = false)
	}
}