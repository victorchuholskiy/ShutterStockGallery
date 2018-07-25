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
			   @Query("per_page") count: Int,
			   @Query("query") query: String): Observable<ImagesResponse>

	/*Unfortunately, I could not find information about the default value for the category parameter.
	If you try to pass an invalid value or an empty string, an empty array of images is returned.
	For this reason, the method was divided into two.*/

	@GET("images/search")
	fun images(@Query("page") page: Int,
			   @Query("per_page") count: Int,
			   @Query("category") category: Int,
			   @Query("query") query: String): Observable<ImagesResponse>

	@get:GET("images/categories")
	val categories: Observable<CategoryResponse>
}