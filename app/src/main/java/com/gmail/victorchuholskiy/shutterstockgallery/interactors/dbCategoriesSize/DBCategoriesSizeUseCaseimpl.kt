package com.gmail.victorchuholskiy.shutterstockgallery.interactors.dbCategoriesSize

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Observable

/**
 * Created by user
 * 26.07.2018.
 */
class DBCategoriesSizeUseCaseImpl: DBCategoriesSizeUseCase {
	override fun execute(): Observable<Long> {
		return RXSQLite.rx(SQLite.selectCountOf()
				.from(Categories::class.java))
				.longValue()
				.toObservable()
	}
}