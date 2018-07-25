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

	override fun getImages(page: Int,
						   count: Int,
						   category: Int,
						   search: String): Observable<ImagesResponse> {
		if (category >= 0) {
			return mApiService.images(page, count, category, search)
		}
		return mApiService.images(page, count, search)
	}

	override fun getCategories(): Observable<CategoryResponse> {
		return mApiService.categories
	}
}
