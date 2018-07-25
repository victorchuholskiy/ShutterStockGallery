package com.gmail.victorchuholskiy.shutterstockgallery.data.source.local

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
@Database(name = GalleryDatabase.name, version = GalleryDatabase.version)
object GalleryDatabase {
	const val name = "GalleryDatabase"
	const val version = 1
}