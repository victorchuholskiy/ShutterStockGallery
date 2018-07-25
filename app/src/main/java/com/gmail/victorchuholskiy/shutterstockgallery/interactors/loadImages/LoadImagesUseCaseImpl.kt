package com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadImages

import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.RestClientImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by user
 * 25.07.2018.
 */
class LoadImagesUseCaseImpl(private val page: Int, private val category: Int, private val query: String) : LoadImagesUseCase {

	override fun execute(): Observable<List<ImageModel>> {
		return RestClientImpl
				.getImages(page, category = category, search = query)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.map({ response ->
					val images = ArrayList<ImageModel>()
					if (response.data != null) {
						for (responseImage in response.data) {
							images.add(ImageModel(
									responseImage.id,
									responseImage.aspect,
									responseImage.assets!!.hugeThumb!!.url,
									responseImage.assets.hugeThumb!!.height,
									responseImage.assets.hugeThumb.width))
						}
					}
					images
				})
	}
}