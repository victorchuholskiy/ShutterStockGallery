package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.BuildConfig
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
object ShutterStockRestClientImpl : ShutterStockRestClient {

	private val mApiService: ShutterStockApiService = createService(
			ShutterStockApiService::class.java,
			baseUrl,
			Gson(),
			ShutterStockInterceptor(BuildConfig.CLIEN_ID, BuildConfig.CLIENT_SECRET))

	override fun getCategories(): Observable<CategoryResponse> {
		return mApiService.categories
	}
}
