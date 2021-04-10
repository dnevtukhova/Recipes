package com.dnevtukhova.core_impl

import com.dnevtukhova.core_api.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent:  NetworkProvider