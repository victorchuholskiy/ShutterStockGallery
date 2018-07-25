package com.gmail.victorchuholskiy.shutterstockgallery.filter

import android.content.Context
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables.Categories
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.rx2.language.RXSQLite

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
	private lateinit var queryText: String
	private var categoryId: Int = -1

	private val categories = ArrayList<Categories>()

	override fun start() {
		view.setQueryText(queryText)
		if (categories.isEmpty()) {
			RXSQLite.rx(
					SQLite.select().from<Categories>(Categories::class.java))
					.queryList()
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

	override fun setData(context: Context?, queryText: String, categoryId: Int) {
		defCategoryName = context!!.getString(R.string.all)
		this.queryText = queryText
		this.categoryId = categoryId
	}

	override fun btnApplyClicked(queryText: String, categoryPos: Int) {
		view.finishActivity(queryText, if (categoryPos > 0) categories[categoryPos - 1].id else -1)
	}

	override fun btnResetClicked() {
		view.setQueryText("")
		view.setSpinnerPosition(0)
	}
}