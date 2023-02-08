package com.fireblade.filehierarchy.home.presentation

sealed class HomeViewIntent : Intent<HomeViewModelState> {
    object LoadCurrentUser: HomeViewIntent()
    data class LoadChildItems(val rootItemId: String) : HomeViewIntent()
    data class NavigateToFolder(val item: FileViewItem) : HomeViewIntent()
    data class NavigateBackToFolder(val navigationHistory: List<FileViewItem>) : HomeViewIntent()
    data class ShowImage(val imageId: String) : HomeViewIntent()
}