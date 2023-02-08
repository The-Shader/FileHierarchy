package com.fireblade.network.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
{"firstName":"Noel","lastName":"Flantier","rootItem":{"id":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","parentId":"","name":"dossierTest","isDir":true,"modificationDate":"2021-11-29T10:57:13Z"}}
 */

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