package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
class ImagesResponse {

	@SerializedName("page")
	val page: Int = 0

	@SerializedName("per_page")
	val perPage: Int = 0

	@SerializedName("total_count")
	val totalCount: Long = 0

	@SerializedName("search_id")
	val searchId: String? = null

	@SerializedName("data")
	val data: List<Image>? = null

	inner class Image {

		@SerializedName("id")
		val id: String = ""

		@SerializedName("aspect")
		val aspect: Double = 0.0

		@SerializedName("assets")
		val assets: Assets? = null

		@SerializedName("contributor")
		val contributor: Contributor? = null

		@SerializedName("description")
		val description: String? = null

		@SerializedName("image_type")
		val imageType: String? = null

		@SerializedName("media_type")
		val mediaType: String? = null

		inner class Assets {

			@SerializedName("preview")
			val preview: ImageData? = null

			@SerializedName("small_thumb")
			val smallThumb: ImageData? = null

			@SerializedName("large_thumb")
			val largeThumb: ImageData? = null

			@SerializedName("huge_thumb")
			val hugeThumb: ImageData? = null

			inner class ImageData {
				@SerializedName("height")
				val height: Int = 0

				@SerializedName("url")
				val aspect: String? = null

				@SerializedName("width")
				val width: Int = 0
			}
		}

		inner class Contributor {

			@SerializedName("id")
			val id: Int = 0
		}
	}
}