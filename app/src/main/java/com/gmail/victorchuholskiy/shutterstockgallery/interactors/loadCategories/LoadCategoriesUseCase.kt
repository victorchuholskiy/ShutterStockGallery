package com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadCategories

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.interactors.UseCase
import io.reactivex.Observable

/**
 * Created by user
 * 26.07.2018.
 */
interface LoadCategoriesUseCase: UseCase<List<Categories>> {
	override fun execute(): Observable<List<Categories>>
}