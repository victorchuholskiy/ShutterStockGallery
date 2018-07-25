package com.gmail.victorchuholskiy.shutterstockgallery.filter

import android.content.Context
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.gmail.victorchuholskiy.shutterstockgallery.interactors.loadCategories.LoadCategoriesUseCaseImpl
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_CATEGORY_ID
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_QUERY_TEXT

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class FilterPresenter(private val view: FilterContract.View)
	: FilterContract.Presenter {

	init {
		view.presenter = this
	}

	private lateinit var defCategoryName: String

	private var queryText: String = DEF_QUERY_TEXT
	private var categoryId: Int = DEF_CATEGORY_ID

	private val categories = ArrayList<Categories>()

	override fun start() {
		view.setQueryText(queryText)
		if (categories.isEmpty()) {
			LoadCategoriesUseCaseImpl()
					.execute()
					.subscribe { list ->
						categories.addAll(list)
						val names = ArrayList<String>()
						var defPos = 0
						names.add(defCategoryName)
						for (i in 0 until categories.size) {
							names.add(categories[i].name!!)
							if (categories[i].id == categoryId) {
								defPos = i + 1
							}
						}
						view.initCategoriesSpinner(names, defPos)
					}
		}
	}

	override fun setData(context: Context?,
						 queryText: String,
						 categoryId: Int) {
		defCategoryName = context!!.getString(R.string.all)
		this.queryText = queryText
		this.categoryId = categoryId
	}

	override fun btnApplyClicked(queryText: String,
								 categoryPos: Int) {
		view.finishActivity(queryText, if (categoryPos > 0) categories[categoryPos - 1].id else -1)
	}

	override fun btnResetClicked() {
		queryText = DEF_QUERY_TEXT
		categoryId = DEF_CATEGORY_ID
		view.setQueryText(queryText)
		view.setSpinnerPosition(0)
	}
}