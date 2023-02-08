package com.fireblade.filehierarchy.home.presentation

import com.fireblade.repository.IFileExplorerService
import com.fireblade.repository.model.ItemInfo

class HomeViewModel(
    private val fileExplorerService: IFileExplorerService
) : MviViewModel<HomeViewIntent, HomeViewState, HomeViewModelState>(
initialState = HomeViewModelState()
) {
    override fun reduce(state: HomeViewModelState): HomeViewState {
        return HomeViewState(
            navigationHistory = state.navigationHistory,
            childItems = state.childItems,
            rawImage = state.rawImage
        )
    }

    override suspend fun handleIntent(modelState: HomeViewModelState, intent: HomeViewIntent) {
        when(intent) {
            HomeViewIntent.LoadCurrentUser -> fileExplorerService.getCurrentUser()
                .fold(
                    ifLeft = {
                    },
                    ifRight = { currentUser ->
                        updateState {
                            modelState.copy(
                                navigationHistory = listOf(currentUser.rootItem.toViewItem())
                            )
                        }
                        onIntent(HomeViewIntent.LoadChildItems(rootItemId = currentUser.rootItem.id))
                    }
                )
            is HomeViewIntent.LoadChildItems -> fileExplorerService.getItemInfo(intent.rootItemId)
                .fold(
                    ifLeft = {
                    },
                    ifRight = { items ->
                        updateState {
                            modelState.copy(
                                childItems = items.map { it.toViewItem() }
                            )
                        }
                    }
                )
            is HomeViewIntent.NavigateToFolder -> fileExplorerService.getItemInfo(intent.item.id)
                .fold(
                    ifLeft = {
                    },
                    ifRight = { items ->
                        updateState {
                            modelState.copy(
                                navigationHistory = modelState.navigationHistory.plus(intent.item),
                                childItems = items.map { it.toViewItem() }
                            )
                        }

                    }
                )
            is HomeViewIntent.NavigateBackToFolder -> {
                val newHistory = intent.navigationHistory.dropLast(1)
                fileExplorerService.getItemInfo(newHistory.last().id)
                    .fold(
                        ifLeft = {
                        },
                        ifRight = { items ->
                            updateState {
                                modelState.copy(
                                    navigationHistory = newHistory,
                                    childItems = items.map { it.toViewItem() }
                                )
                            }
                        }
                    )
            }
            is HomeViewIntent.ShowImage -> fileExplorerService.downloadItem(intent.imageId)
                .fold(
                    ifLeft = {
                    },
                    ifRight = { rawImage ->
                        updateState {
                            modelState.copy(
                                rawImage = rawImage
                            )
                        }
                    }
                )
        }
    }
}

fun ItemInfo.toViewItem() =
    FileViewItem(
        id = id,
        modificationDate = modificationDate,
        name = name,
        parentId = parentId,
        size = size,
        contentType = when {
            isDir -> ContentType.FOLDER
            contentType?.contains("image") == true -> ContentType.IMAGE
            else -> ContentType.OTHER
        }
    )