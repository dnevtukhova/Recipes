package com.dnevtukhova.core_api

import com.dnevtukhova.core_api.database.DataBaseProvider
import com.dnevtukhova.core_api.mediators.MediatorsProvider
import com.dnevtukhova.core_api.navigation.NavigationProvider
import com.dnevtukhova.core_api.network.NetworkProvider

interface ProvidersFacade : MediatorsProvider, NavigationProvider, NetworkProvider, AppProvider, DataBaseProvider