package com.gmail.victorchuholskiy.shutterstockgallery.splash

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.interactors.cachingCategories.CachingCategoriesUseCaseImpl
import com.raizlabs.android.dbflow.sql.language.SQLite

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class SplashPresenter(private val view: SplashContract.View)
	: SplashContract.Presenter {

	init {
		view.presenter = this
	}

	override fun start() {
		if (SQLite.selectCountOf().from(Categories::class.java).longValue() == 0L) {
			CachingCategoriesUseCaseImpl()
					.execute()
					.subscribe({ result ->
						when (result) {
							true -> view.navigateNextScreen()
							false -> view.closeApp()
						}
					}, { exception ->
						with(view) {
							showError(exception.message!!)
							closeApp()
						}
					})
		} else {
			view.navigateNextScreen()
		}
	}
}