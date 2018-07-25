package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.ImagesResponse
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_CATEGORY_ID
import com.gmail.victorchuholskiy.shutterstockgallery.utils.DEF_QUERY_TEXT
import com.gmail.victorchuholskiy.shutterstockgallery.utils.PAGE_COUNT_IMAGES
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 *
 * Main REST interface that describes necessary API methods and contains base URL
 */
interface RestClient {

	/**
	 * Base URL of ShutterStock Api.
	 * The link is unchanged, so it's logical to store it directly in the interface that defines the required set of methods.
	 */
	val baseUrl : String
		get() = "https://api.shutterstock.com/v2/"

	fun getImages(page: Int,
				  count: Int = PAGE_COUNT_IMAGES,
				  category: Int = DEF_CATEGORY_ID,
				  search : String = DEF_QUERY_TEXT) : Observable<ImagesResponse>

	fun getCategories() : Observable<CategoryResponse>
}