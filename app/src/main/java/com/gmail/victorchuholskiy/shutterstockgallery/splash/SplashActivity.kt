package com.gmail.victorchuholskiy.shutterstockgallery.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import com.gmail.victorchuholskiy.shutterstockgallery.main.MainActivity

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
class SplashActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		startActivity(Intent(this@SplashActivity, MainActivity::class.java))
		finish()
	}
}