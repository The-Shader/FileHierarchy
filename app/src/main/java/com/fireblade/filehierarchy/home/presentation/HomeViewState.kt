package com.fireblade.filehierarchy.home.presentation

data class HomeViewState(
    val navigationHistory: List<FileViewItem> = listOf(),
    val childItems: List<FileViewItem>,
    val rawImage: ByteArray?
) : ViewState