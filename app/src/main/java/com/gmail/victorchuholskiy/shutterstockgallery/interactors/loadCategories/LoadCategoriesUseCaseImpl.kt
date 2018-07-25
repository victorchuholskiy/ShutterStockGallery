package com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadCategories

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Observable

/**
 * Created by user
 * 26.07.2018.
 */
class LoadCategoriesUseCaseImpl: LoadCategoriesUseCase {
	override fun execute(): Observable<List<Categories>> {
		return RXSQLite.rx(SQLite.select().from<Categories>(Categories::class.java))
				.queryList()
				.toObservable()
	}
}