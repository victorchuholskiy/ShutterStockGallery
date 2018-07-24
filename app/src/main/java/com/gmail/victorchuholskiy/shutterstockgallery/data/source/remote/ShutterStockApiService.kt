package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.ImagesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
internal interface ShutterStockApiService {

	@get:GET("images/search")
	val images: Observable<ImagesResponse>

	@get:GET("images/categories")
	val categories: Observable<CategoryResponse>
}