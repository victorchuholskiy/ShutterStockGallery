package com.gmail.victorchuholskiy.shutterstockgallery.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.utils.ARG_CATEGORY_ID
import com.gmail.victorchuholskiy.shutterstockgallery.utils.ARG_QUERY_TEXT
import com.gmail.victorchuholskiy.shutterstockgallery.utils.replaceFragmentInActivity
import com.gmail.victorchuholskiy.shutterstockgallery.utils.setupActionBar

class FilterActivity : AppCompatActivity() {

	private lateinit var presenter: FilterPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_filter)

		// Set up the toolbar.
		setupActionBar(R.id.toolbar) {
			setDisplayHomeAsUpEnabled(true)
			setDisplayShowHomeEnabled(true)
		}

		val galleryFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
				as FilterFragment? ?: FilterFragment.newInstance(intent.extras).also {
			replaceFragmentInActivity(it, R.id.contentFrame)
		}

		// Create the presenter
		presenter = FilterPresenter(galleryFragment)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home) {
			onBackPressed()
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {
		fun getIntent(context: Context, queryText: String, categoryId: Int): Intent {
			val intent = Intent(context, FilterActivity::class.java)
			intent.putExtra(ARG_QUERY_TEXT, queryText)
			intent.putExtra(ARG_CATEGORY_ID, categoryId)
			return intent
		}
	}
}
