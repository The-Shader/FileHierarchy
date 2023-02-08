package com.fireblade.network

import arrow.core.Either
import com.fireblade.network.content.ItemInfoResponse
import com.fireblade.network.user.GetCurrentUserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface IFileNetworkService {

    @GET("/me")
    suspend fun getCurrentUser(
        @Header("Authorization") auth: String
    ): Either<String, GetCurrentUserResponse>

    @GET("/items/{id}")
    suspend fun getFolder(
        @Header("Authorization") auth: String,
        @Path("id") folderId: String
    ): Either<String, List<ItemInfoResponse>>


    @GET("/items/{id}/data")
    @Headers("Content-Type:application/octet-stream")
    suspend fun downloadItem(
        @Header("Authorization") auth: String,
        @Path("id") itemId: String
    ): Either<String, ResponseBody>
}