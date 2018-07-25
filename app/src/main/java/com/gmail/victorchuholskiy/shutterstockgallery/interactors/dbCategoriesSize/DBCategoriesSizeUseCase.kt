package com.gmail.victorchuholskiy.shutterstockgallery.interactors.dbCategoriesSize

import com.gmail.victorchuholskiy.shutterstockgallery.interactors.UseCase
import io.reactivex.Observable

/**
 * Created by user
 * 26.07.2018.
 */
interface DBCategoriesSizeUseCase: UseCase<Long> {
	override fun execute(): Observable<Long>
}