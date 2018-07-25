package com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.tables

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.local.GalleryDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.structure.BaseModel
import com.raizlabs.android.dbflow.annotation.Table

/**
 * Created by viktor.chukholskiy
 * 25/07/18.
 */
@Table(database = GalleryDatabase::class)
class Categories() : BaseModel() {

	constructor(id: Int, name: String) : this() {
		this.id = id
		this.name = name
	}

	@Column
	@PrimaryKey
	var id: Int = 0

	@Column
	var name: String? = null
}