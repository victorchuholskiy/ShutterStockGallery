package com.gmail.victorchuholskiy.shutterstockgallery.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.victorchuholskiy.shutterstockgallery.R
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.ShutterStockRestClientImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		ShutterStockRestClientImpl
				.getImages()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({response -> if (response.data != null && response.data.isNotEmpty()) {
					Log.d("AAA", response.toString())
				}})
	}
}
