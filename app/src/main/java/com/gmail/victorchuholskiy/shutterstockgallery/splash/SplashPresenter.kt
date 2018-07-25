package com.gmail.victorchuholskiy.shutterstockgallery.splash

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.RestClientImpl
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
			RestClientImpl
					.getCategories()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({ response ->
						if (response.data != null && response.data.isNotEmpty()) {
							for (category in response.data) {
								Categories(category.id!!, category.name!!).save()
							}
							view.navigateNextScreen()
						} else {
							view.closeApp()
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