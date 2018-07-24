package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 *
 * Main REST interface that describes necessary API methods and contains base URL
 */
interface ShutterStockRestClient {

	/**
	 * Base URL of ShutterStock Api.
	 * The link is unchanged, so it's logical to store it directly in the interface that defines the required set of methods.
	 */
	val baseUrl : String
		get() = "https://api.shutterstock.com/v2/"

	fun getCategories() : Observable<CategoryResponse>
}