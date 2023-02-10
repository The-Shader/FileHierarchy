package com.fireblade.network.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentUserResponse(
    @SerialName("firstName")
    val  firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("rootItem")
    val rootItem: ItemResponse
)

@Serializable
data class ItemResponse(
    @SerialName("id")
    val id: String,
    @SerialName("parentId")
    val parentId: String?,
    @SerialName("isDir")
    val isDir: Boolean,
    @SerialName("modificationDate")
    val modificationDate: String,
    @SerialName("name")
    val name: String,
    @SerialName("size")
    val size: Int?,
    @SerialName("contentType")
    val contentType: String?
)