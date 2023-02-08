package com.fireblade.repository.model

data class ItemInfo(
    val id: String,
    val isDir: Boolean,
    val modificationDate: String,
    val name: String,
    val parentId: String?,
    val size: Int?,
    val contentType: String?
)