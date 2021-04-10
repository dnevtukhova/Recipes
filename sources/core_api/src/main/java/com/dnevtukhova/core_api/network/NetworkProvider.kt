package com.dnevtukhova.core_api.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkProvider {
    fun provideRetrofit(): Retrofit
    fun provideOkHttp(): OkHttpClient
    fun provideRetrofitBuilder(): Retrofit.Builder
  // fun serverApi(): ServerApi
  //  fun provideServerApi(): ServerApiProvider
}