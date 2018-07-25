package com.gmail.victorchuholskiy.shutterstockgallery.gallery

import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.RestClientImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class GalleryPresenter(private val view: GalleryContract.View)
	: GalleryContract.Presenter {

	init {
		view.presenter = this
	}

	override fun start() {
		loadImages(1, 40)
	}

	override fun loadImages(page: Int, category: Int, search: String, clear : Boolean) {
		view.showProgress()
		RestClientImpl
				.getImages(page)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{ response ->
							if (response.data != null && response.data.isNotEmpty()) {
								val images = ArrayList<ImageModel>()
								for (responseImage in response.data) {
									images.add(ImageModel(
											responseImage.id,
											responseImage.aspect,
											responseImage.assets!!.hugeThumb!!.url,
											responseImage.assets.hugeThumb!!.height,
											responseImage.assets.hugeThumb.width))
								}
								view.showImages(images, clear)
								view.hideProgress()
							}
						},
						{ error ->
							view.showError("error")
							view.hideProgress()
						})
	}
}