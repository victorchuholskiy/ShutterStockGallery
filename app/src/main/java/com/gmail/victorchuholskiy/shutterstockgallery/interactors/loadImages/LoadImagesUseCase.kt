package com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadImages

import com.gmail.victorchuholskiy.shutterstockgallery.data.models.ImageModel
import com.gmail.victorchuholskiy.shutterstockgallery.interactors.UseCase
import io.reactivex.Observable

/**
 * Created by user
 * 25.07.2018.
 */
interface LoadImagesUseCase : UseCase<List<ImageModel>> {
	override fun execute(): Observable<List<ImageModel>>
}