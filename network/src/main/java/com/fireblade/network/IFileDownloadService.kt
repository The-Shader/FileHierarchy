package com.fireblade.network

import arrow.core.Either
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import java.io.ByteArrayInputStream

interface IFileDownloadService {
    @GET("/items/{id}/data")
    @Headers("Content-Type:application/octet-stream")
    suspend fun downloadItem(
        @Header("Authorization") auth: String,
        @Path("id") itemId: String
    ): Either<String, ByteArrayInputStream>
}