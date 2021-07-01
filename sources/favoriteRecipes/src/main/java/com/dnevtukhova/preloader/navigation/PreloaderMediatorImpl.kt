package com.dnevtukhova.preloader.navigation

import com.dnevtukhova.core_api.mediators.PreloaderMediator
import com.dnevtukhova.preloader.presentation.PreloaderFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class PreloaderMediatorImpl @Inject constructor(): PreloaderMediator {
    override fun startPreloaderFragment(): FragmentScreen {
        return FragmentScreen{PreloaderFragment.getNewInstance()}
    }
}