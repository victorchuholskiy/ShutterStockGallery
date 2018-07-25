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

	private var queryText: String = ""
	private var categoryId: Int = -1

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

	override fun loadImages(page: Int, clear: Boolean) {
		view.showProgress()
		RestClientImpl
				.getImages(page, category = categoryId, search = queryText)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{ response ->
							if (response.data != null) {
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