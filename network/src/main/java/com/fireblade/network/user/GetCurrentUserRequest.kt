package com.fireblade.network.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentUserRequest(
    @SerialName("firstName")
    val  firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("rootItem")
    val rootItem: Item
)

@Serializable
data class Item(
    @SerialName("id")
    val id: String,
    @SerialName("isDir")
    val isDir: Boolean,
    @SerialName("modificationDate")
    val modificationDate: String,
    @SerialName("name")
    val name: String
)