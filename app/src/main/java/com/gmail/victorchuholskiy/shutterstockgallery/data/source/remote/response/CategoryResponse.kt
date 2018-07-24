package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
class CategoryResponse {

	@SerializedName("data")
	val data: List<Category>? = null

	inner class Category {
		@SerializedName("id")
		val id: Int = 0

		@SerializedName("name")
		val name: String? = null
	}
}