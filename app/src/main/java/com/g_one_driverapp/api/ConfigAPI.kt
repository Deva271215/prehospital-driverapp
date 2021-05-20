package com.g_one_driverapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigAPI {
    companion object {
//        private const val PROD_URL = "https://canary-dot-g-one-db.an.r.appspot.com/"
        private const val DEV_URL = "http://localhost:8080/"
    }

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder().method(original.method, original.body)
                val request = requestBuilder.build()

                it.proceed(request)
            }.build()

    val instance: ServiceAPI by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(DEV_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        retrofit.create(ServiceAPI::class.java)
    }
}