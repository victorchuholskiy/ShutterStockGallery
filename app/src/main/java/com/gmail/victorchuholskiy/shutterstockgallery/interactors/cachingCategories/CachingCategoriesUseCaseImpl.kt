package com.gmail.victorchuholskiy.shutterstockgallery.interactors.cachingCategories

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.GalleryDatabase
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.RestClientImpl
import com.raizlabs.android.dbflow.config.FlowManager
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
				.map {
					if (it.data != null && it.data.isNotEmpty()) {
						val list = ArrayList<Categories>()
						for (category in it.data) {
							list.add(Categories(category.id!!, category.name!!))
						}
						FlowManager.getDatabase(GalleryDatabase.javaClass)
								.transactionManager
								.saveQueue
								.addAll(list as Collection<Categories>)
						true
					} else {
						false
					}
				}
	}
}