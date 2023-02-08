package com.fireblade.network.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
[{"id":"ff88427d659ab87da9e83afa3b8154dcb6f5f105","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"IMG-20230109-WA0009.jpg","isDir":false,"size":409707,"contentType":"image/jpeg","modificationDate":"2023-01-10T15:31:21.15949797Z"},{"id":"33ed725ba84b660cb1090b242bc952d41a9234b1","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"IMG_0002.jpg","isDir":false,"size":3031934,"contentType":"image/jpeg","modificationDate":"2023-01-04T19:51:22.417047223Z"},{"id":"9a70dfa8eba62c47e059445b2edd797fa3580fb5","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"IMG_0003.jpg","isDir":false,"size":2340028,"contentType":"image/jpeg","modificationDate":"2023-01-04T19:50:50.884713852Z"},{"id":"49d4009615aeeb2a98e45b83dd9955fe29935676","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"IMG_1674751801771.jpg","isDir":false,"size":970309,"contentType":"image/jpeg","modificationDate":"2023-01-26T16:50:02.609205214Z"},{"id":"c6ca2159b330d2ecd30ffc0fa6a803568c519e4b","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"Nico Duponchel","isDir":true,"modificationDate":"2023-01-26T16:49:38.736961314Z"},{"id":"493122e660d63f0603eb6b7f754d7f57c2f4e776","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"Peter","isDir":true,"modificationDate":"2023-01-28T15:11:26.081320901Z"},{"id":"d68f4ba9fee3569689db9b8935d4792ffcebe955","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"dan","isDir":true,"modificationDate":"2023-01-17T11:03:25.98387983Z"},{"id":"82a06b9e18ab2cba3c8edf379aa15eb482df0a1d","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"gus","isDir":true,"modificationDate":"2023-01-21T17:49:15.617600241Z"},{"id":"37678569f292d88ba1abd9c583fa99c2bc2d0650","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"peloncho","isDir":true,"modificationDate":"2023-01-29T19:44:43.208095231Z"},{"id":"22ed3ee44f53ac824ee1ec451d7900190eeac074","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"temp","isDir":true,"modificationDate":"2023-01-15T20:09:45.774491533Z"},{"id":"0510c42518a9ecc162265ee3ea7fdb8faa9e9252","parentId":"4b8e41fd4a6a89712f15bbf102421b9338cfab11","name":"ttt","isDir":true,"modificationDate":"2023-01-17T09:12:24.921734633Z"}]
 */

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