package com.dnevtukhova.recipes

import android.app.Application
import com.dnevtukhova.recipes.data.api.NetworkConstants.BASE_URL
import com.dnevtukhova.recipes.data.api.ServerApi
import com.dnevtukhova.recipes.domain.RecipesInteractor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var recipesInteractor: RecipesInteractor
    lateinit var api: ServerApi

    override fun onCreate() {
        super.onCreate()
       instance = this
        api = initRetrofit()
        recipesInteractor = RecipesInteractor(api)

    }

    private fun initRetrofit(): ServerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                if (BuildConfig.DEBUG) {
                                    level = HttpLoggingInterceptor.Level.BODY
                                }
                            })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())

            .build()
            .create(ServerApi::class.java)
    }
}