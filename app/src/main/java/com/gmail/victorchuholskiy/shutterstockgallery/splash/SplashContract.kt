package com.gmail.victorchuholskiy.shutterstockgallery.splash

import com.gmail.victorchuholskiy.shutterstockgallery.BasePresenter
import com.gmail.victorchuholskiy.shutterstockgallery.BaseView

/**
 * Created by viktor.chukholskiy
 * 25/07/18.

 * This specifies the contract between the view and the presenter.
 */
interface SplashContract {

	interface View : BaseView<Presenter> {

		fun navigateNextScreen()

		fun showError(msg: String)

		fun closeApp()
	}

	interface Presenter : BasePresenter
}