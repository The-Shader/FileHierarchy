package com.fireblade.filehierarchy.home.presentation

data class HomeViewModelState(
    val navigationHistory: List<FileViewItem> = listOf(),
    val childItems: List<FileViewItem> = listOf(),
    val rawImage: ByteArray? = null
) : ModelState