package com.dnevtukhova.core_api.mediators

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface PreloaderMediator {
    fun startPreloaderFragment(): FragmentScreen
}