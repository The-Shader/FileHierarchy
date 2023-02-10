package com.fireblade.network.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemInfoResponse(
    @SerialName("id")
    val id: String,
    @SerialName("isDir")
    val isDir: Boolean,
    @SerialName("modificationDate")
    val modificationDate: String,
    @SerialName("name")
    val name: String,
    @SerialName("parentId")
    val parentId: String?,
    @SerialName("size")
    val size: Int?,
    @SerialName("contentType")
    val contentType: String?
)