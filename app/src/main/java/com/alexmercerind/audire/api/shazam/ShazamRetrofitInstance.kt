package com.alexmercerind.audire.api.shazam

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ShazamRetrofitInstance {
    companion object {

        private const val BASE_URL = "https://amp.shazam.com/"

        private val instance: Retrofit by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: ShazamAPI by lazy {
            instance.create(ShazamAPI::class.java)
        }
    }
}