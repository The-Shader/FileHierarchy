package com.fireblade.repository.model

data class CurrentUser(
    val firstName: String,
    val lastName: String,
    val rootItem: ItemInfo
)