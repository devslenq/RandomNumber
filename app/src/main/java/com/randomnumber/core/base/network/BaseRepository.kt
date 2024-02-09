package com.randomnumber.core.base.network

import retrofit2.Response

open class BaseRepository {
    fun <T> networkRequest(request: () -> Response<T>): Either<Failure, T> =
        try {
            val response = request()
            val result = response.getEither()
            result
        } catch (ex: Exception) {
            ex.printStackTrace()
            Either.Left(Failure.ServerError)

        }
}

fun <T> Response<T>.getEither(): Either<Failure, T> =
    when (this.code()) {
        200 -> {
            body()?.let {
                Either.Right(it)
            } ?: Either.Left(Failure.ServerErrorWithDescription(this.code(), this.message()))
        }

        in 400..499 -> {
            Either.Left(Failure.ServerErrorWithDescription(this.code(), this.message()))
        }

        else -> {
            Either.Left(Failure.ServerError)
        }
    }

sealed class Failure {
    object ServerError : Failure()
    open class ServerErrorWithDescription(val code: Int, val message: String?) : Failure()

}