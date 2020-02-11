package com.tecknoworks.poq.data

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */
sealed class PoqResult<T> {

    data class Success<T>(val value: T) : PoqResult<T>()

    data class Failure<T>(val throwable: Throwable) : PoqResult<T>()

}