package com.fireblade.filehierarchy.home.presentation

data class FileViewItem(
    val id: String,
    val modificationDate: String,
    val name: String,
    val parentId: String?,
    val size: Int?,
    val contentType: ContentType
)

enum class ContentType {
    FOLDER,
    IMAGE,
    OTHER
}