package com.randomnumber.di

import android.content.Context
import android.util.Log
import com.randomnumber.ApplicationConfig
import com.randomnumber.core.data.network.Api
import com.randomnumber.core.data.network.intercept.HeaderModifierInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApplicationConfig.base_url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideClient(
    headerInterceptor: HeaderModifierInterceptor
): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient().newBuilder()
        .addInterceptor(LogNetwork())
        .addInterceptor(headerInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .retryOnConnectionFailure(true)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

class LogNetwork : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        response.run {
            Log.d("LogNetwork", "${request.method} >> ${code} >> ${request.url} >> ${message}")
        }
        return response
    }

}