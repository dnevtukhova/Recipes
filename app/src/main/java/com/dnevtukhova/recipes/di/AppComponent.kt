package com.dnevtukhova.recipes.di

import com.dnevtukhova.recipes.App
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        NavigationModule::class]
)
interface AppComponent {
    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.create()
        }
    }

    fun provideRetrofit(): Retrofit

    fun provideRouter(): Router

    fun provideNavigatorHolder(): NavigatorHolder

    fun inject(app: App)
}