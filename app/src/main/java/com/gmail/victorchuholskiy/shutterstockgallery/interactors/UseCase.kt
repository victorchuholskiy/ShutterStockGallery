package com.gmail.victorchuholskiy.shutterstockgallery.interactors

import io.reactivex.Observable

/**
 * Created by viktor.chukholskiy
 * 25.07.2018.
 *
 * Base use case
 */
interface UseCase<RESPONSE> {
	fun execute(): Observable<RESPONSE>
}