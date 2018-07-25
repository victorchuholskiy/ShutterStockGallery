package com.gmail.victorchuholskiy.shutterstockgallery.interactors.cachingCategories

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.RestClientImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by user
 * 26.07.2018.
 */
class CachingCategoriesUseCaseImpl: CachingCategoriesUseCase {
	override fun execute(): Observable<Boolean> {
		return RestClientImpl
				.getCategories()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.map({ response ->
					if (response.data != null && response.data.isNotEmpty()) {
						for (category in response.data) {
							Categories(category.id!!, category.name!!).save()
						}
						true
					} else {
						false
					}
				})
	}
}