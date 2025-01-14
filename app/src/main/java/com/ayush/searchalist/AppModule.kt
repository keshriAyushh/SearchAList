package com.ayush.searchalist

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SearchViewModel() }
}