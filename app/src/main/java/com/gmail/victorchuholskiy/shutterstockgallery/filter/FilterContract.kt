package com.gmail.victorchuholskiy.shutterstockgallery.filter

import android.content.Context
import com.gmail.victorchuholskiy.shutterstockgallery.BasePresenter
import com.gmail.victorchuholskiy.shutterstockgallery.BaseView

/**
 * Created by viktor.chukholskiy
 * 25/07/18.

 * This specifies the contract between the view and the presenter.
 */
interface FilterContract {

	interface View : BaseView<Presenter> {

		fun initCategoriesSpinner(categories: List<String>, position: Int)

		fun setSpinnerPosition(position: Int)

		fun setQueryText(text: String)

		fun finishActivity(queryText: String, categoryId: Int)
	}

	interface Presenter : BasePresenter {

		fun setData(context: Context?, queryText: String, categoryId: Int)

		fun btnApplyClicked(queryText: String, categoryPos: Int)

		fun btnResetClicked()
	}
}