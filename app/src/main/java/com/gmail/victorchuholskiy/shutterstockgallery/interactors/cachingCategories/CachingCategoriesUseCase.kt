package com.gmail.victorchuholskiy.shutterstockgallery.interactors.cachingCategories

import com.gmail.victorchuholskiy.shutterstockgallery.interactors.UseCase
import io.reactivex.Observable

/**
 * Created by user
 * 26.07.2018.
 */
interface CachingCategoriesUseCase : UseCase<Boolean> {
	override fun execute(): Observable<Boolean>
}