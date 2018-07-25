package com.gmail.victorchuholskiy.shutterstockgallery

import android.app.Application
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.GalleryDatabase
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
class ShutterStockApp : Application() {
	override fun onCreate() {
		super.onCreate()

		// This instantiates DBFlow
		FlowManager.init(FlowConfig.Builder(this)
				.addDatabaseConfig(DatabaseConfig.Builder(GalleryDatabase::class.java).build()).build())
	}
}