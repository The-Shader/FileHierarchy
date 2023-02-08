package com.fireblade.filehierarchy.home

import com.fireblade.filehierarchy.home.presentation.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    single {
        HomeViewModel(
            fileExplorerService = get()
        )
    }
}