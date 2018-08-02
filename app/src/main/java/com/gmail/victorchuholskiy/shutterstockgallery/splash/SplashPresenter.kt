package com.gmail.victorchuholskiy.shutterstockgallery.splash

import com.gmail.victorchuholskiy.shutterstockgallery.interactors.cachingCategories.CachingCategoriesUseCaseImpl
import com.gmail.victorchuholskiy.shutterstockgallery.interactors.dbCategoriesSize.DBCategoriesSizeUseCaseImpl
import io.reactivex.Observable

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
		DBCategoriesSizeUseCaseImpl()
				.execute()
				.flatMap {
					if (it == 0L)
						CachingCategoriesUseCaseImpl().execute()
					else
						Observable.just<Boolean>(true)
				}
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
	}
}