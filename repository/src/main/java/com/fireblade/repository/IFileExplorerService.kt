package com.fireblade.repository

import arrow.core.Either
import com.fireblade.repository.model.CurrentUser
import com.fireblade.repository.model.Error
import com.fireblade.repository.model.ItemInfo

interface IFileExplorerService {
    suspend fun getCurrentUser(): Either<Error, CurrentUser>

    suspend fun getItemInfo(itemId: String): Either<Error, List<ItemInfo>>

    suspend fun downloadItem(itemId: String): Either<Error, ByteArray>
}