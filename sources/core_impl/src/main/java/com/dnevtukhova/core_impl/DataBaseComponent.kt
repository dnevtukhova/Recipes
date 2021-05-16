package com.dnevtukhova.core_impl

import com.dnevtukhova.core_api.AppProvider
import com.dnevtukhova.core_api.database.DataBaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DataBaseModule::class]
)
interface DataBaseComponent : DataBaseProvider