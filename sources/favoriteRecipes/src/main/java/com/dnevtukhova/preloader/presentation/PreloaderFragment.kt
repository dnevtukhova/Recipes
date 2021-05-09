package com.dnevtukhova.preloader.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dnevtukhova.core_api.AppWithFacade
import com.dnevtukhova.preloader.R
import com.dnevtukhova.preloader.databinding.PreloaderFragmentBinding
import com.dnevtukhova.preloader.di.PreloaderComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

class PreloaderFragment : Fragment() {
    companion object {
        const val TAG = "PreloaderFragment"

        fun getNewInstance(): PreloaderFragment {
            return PreloaderFragment()
        }
    }

    private lateinit var binding: PreloaderFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val preloaderViewModel: PreloaderViewModel
            by viewModels {
                viewModelFactory
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreloaderComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PreloaderFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBlocking {
              //  preloaderViewModel.openRecipesListFragment()


        }

    }



}