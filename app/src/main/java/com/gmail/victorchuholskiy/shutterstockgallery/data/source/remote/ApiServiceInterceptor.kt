package com.gmail.victorchuholskiy.shutterstockgallery.data.source.remote

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by viktor.chukholskiy
 * 24/07/18.
 *
 * Interceptor for Basic Auth.
 * Constructor fields:
 * 		user - client id
 * 		password - client secret
 */
class ApiServiceInterceptor(user: String,
							password: String) : Interceptor {

	private val credentials: String = Credentials.basic(user, password)

	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val authenticatedRequest = request.newBuilder()
				.header("Authorization", credentials).build()
		return chain.proceed(authenticatedRequest)
	}
}