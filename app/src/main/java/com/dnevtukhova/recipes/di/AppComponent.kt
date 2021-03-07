package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.App
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.create()
        }
    }

    fun provideRetrofit(): Retrofit

    fun inject(app: App)
}