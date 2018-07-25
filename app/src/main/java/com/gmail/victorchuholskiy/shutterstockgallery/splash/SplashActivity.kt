package com.gmail.victorchuholskiy.shutterstockgallery.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.gmail.victorchuholskiy.shutterstockgallery.gallery.GalleryActivity

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 *
 * Simple splash activity for solving the problem of blank white page at the start of the app
 * and loading list of categories during the first run
 */
class SplashActivity : AppCompatActivity(), SplashContract.View {

	override lateinit var presenter: SplashContract.Presenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Create the presenter
		presenter = SplashPresenter(this)
	}

	override fun onResume() {
		super.onResume()
		presenter.start()
	}

	override fun navigateNextScreen() {
		startActivity(Intent(this@SplashActivity, GalleryActivity::class.java))
		finish()
	}

	override fun showError(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}

	override fun closeApp() {
		finish()
	}
}