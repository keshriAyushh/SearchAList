package com.ayush.searchalist.di

import com.ayush.searchalist.presentation.screen.SearchViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SearchViewModel() }
}