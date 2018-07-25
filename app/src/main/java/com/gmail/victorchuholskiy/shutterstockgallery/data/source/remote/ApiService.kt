package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.CategoryResponse
import com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote.response.ImagesResponse
import io.reactivex.Observable
import retrofit2.http.Query
import retrofit2.http.GET

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 */
internal interface ApiService {

	@GET("images/search")
	fun images(@Query("page") page: Int,
			   @Query("count") count: Int): Observable<ImagesResponse>

	@get:GET("images/categories")
	val categories: Observable<CategoryResponse>
}