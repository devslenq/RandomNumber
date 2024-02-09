package com.randomnumber.core.data.network.intercept

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderModifierInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.run {
                this.addHeader("Content-Type", "application/json")
                this.addHeader("Accept-Language", "ru-RU")
            }
            return chain.proceed(requestBuilder.build())
        } catch (ex: Exception) {
            throw IOException(ex.message)
        }
    }
}