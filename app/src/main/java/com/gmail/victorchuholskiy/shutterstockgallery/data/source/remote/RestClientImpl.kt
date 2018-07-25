package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.BuildConfig
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.ImagesResponse
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
object RestClientImpl : RestClient {

	private val mApiService: ApiService = createService(
			ApiService::class.java,
			baseUrl,
			Gson(),
			ApiServiceInterceptor(BuildConfig.CLIEN_ID, BuildConfig.CLIENT_SECRET))

	override fun getImages(page: Int, count: Int, category: String, search: String): Observable<ImagesResponse> {
		return mApiService.images(page, count)
	}

	override fun getCategories(): Observable<CategoryResponse> {
		return mApiService.categories
	}
}
