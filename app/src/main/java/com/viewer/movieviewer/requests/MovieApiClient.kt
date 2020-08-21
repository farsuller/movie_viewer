package com.viewer.movieviewer.requests

import com.viewer.movieviewer.util.Constants
import com.viewer.movieviewer.util.Constants.Companion.BASE_URL
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object MovieApiClient {

    fun getClient(): MovieApi{

        val requestInterceptor = Interceptor{ chain ->

            val url : HttpUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()

            val request:Request = chain.request()
                .newBuilder().url(url).build()

            return@Interceptor chain.proceed(request)
        }

         val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieApi::class.java)

    }
}