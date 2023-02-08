package com.fireblade.repository

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        FileExplorerRepository(
            fileNetworkService = get(),
            dispatcher = Dispatchers.IO
        )
    }.bind(IFileExplorerService::class)
}