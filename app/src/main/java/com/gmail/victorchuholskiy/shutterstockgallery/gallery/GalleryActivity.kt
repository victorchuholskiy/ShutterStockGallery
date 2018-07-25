package com.gmail.victorchuholskiy.shutterstockgallery.gallery

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.filter.FilterActivity
import com.gmail.victorchuholskiy.shutterstockgallery.utils.*

class GalleryActivity : AppCompatActivity() {

	private lateinit var presenter: GalleryPresenter

	private var queryText: String = DEF_QUERY_TEXT
	private var categoryId: Int = DEF_CATEGORY_ID

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_gallery)

		// Set up the toolbar.
		setupActionBar(R.id.toolbar) {}

		val galleryFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
				as GalleryFragment? ?: GalleryFragment.newInstance().also {
			replaceFragmentInActivity(it, R.id.contentFrame)
		}

		// Create the presenter
		presenter = GalleryPresenter(galleryFragment)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		val inflater = menuInflater
		inflater.inflate(R.menu.gallery_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.filter) {
			startActivityForResult(FilterActivity.getIntent(this, queryText, categoryId), REQUEST_CODE_FILTER)
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onActivityResult(requestCode: Int,
								  resultCode: Int,
								  data: Intent?) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FILTER) {
			queryText = data!!.getStringExtra(ARG_QUERY_TEXT)
			categoryId = data.getIntExtra(ARG_CATEGORY_ID, DEF_CATEGORY_ID)
			presenter.setQueryText(queryText)
			presenter.setCategoryId(categoryId)
		}
	}
}
