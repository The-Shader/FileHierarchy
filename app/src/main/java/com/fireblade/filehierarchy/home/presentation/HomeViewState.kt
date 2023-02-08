package com.fireblade.filehierarchy.home.presentation

data class HomeViewState(
    val navigationHistory: List<FileViewItem> = listOf(),
    val childItems: List<FileViewItem>,
    val rawImage: ByteArray?,
    val errorStatus: ErrorStatus
) : ViewState

enum class ErrorStatus {
    NONE,
    IMAGE_DOWNLOAD_FAILED,
    CONTENT_FAILED,
    USER_FAILED
}