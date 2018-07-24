package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
internal interface ShutterStockApiService {

	@get:GET("images/categories")
	val categories: Observable<CategoryResponse>
}