package com.uhc.jokes.di

import com.uhc.presentation.MainViewModel
import com.uhc.presentation.joke.JokeAdapter
import com.uhc.presentation.joke.JokesListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Constancio on 2019-11-16.
 */
val presentationModule = module {

    viewModel { MainViewModel() }

    viewModel { JokesListViewModel(get()) }

    factory { JokeAdapter() }
}