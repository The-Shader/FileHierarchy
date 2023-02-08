package com.fireblade.repository

import arrow.core.Either
import com.fireblade.network.IFileNetworkService
import com.fireblade.repository.model.CurrentUser
import com.fireblade.repository.model.Error
import com.fireblade.repository.model.ItemInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials

class FileExplorerRepository(
    private val fileNetworkService: IFileNetworkService,
    private val dispatcher: CoroutineDispatcher
) : IFileExplorerService {
    override suspend fun getCurrentUser(): Either<Error, CurrentUser> {
        return withContext(dispatcher) {
            fileNetworkService.getCurrentUser(
                auth = credentials
            )
                .map {
                    CurrentUser(
                        firstName = it.firstName,
                        lastName = it.lastName,
                        rootItem = ItemInfo(
                            id = it.rootItem.id,
                            isDir = it.rootItem.isDir,
                            modificationDate = it.rootItem.modificationDate,
                            name = it.rootItem.name,
                            parentId = it.rootItem.parentId,
                            size = it.rootItem.size,
                            contentType = it.rootItem.contentType
                        )
                    )
                }
                .mapLeft {
                    Error.GeneralError
                }
        }
    }

    override suspend fun getItemInfo(itemId: String): Either<Error, List<ItemInfo>> {
        return withContext(dispatcher) {
            fileNetworkService.getFolder(
                auth = credentials,
                folderId = itemId
            )
                .map { itemResponses ->
                    itemResponses.map { response ->
                        ItemInfo(
                            id = response.id,
                            isDir = response.isDir,
                            modificationDate = response.modificationDate,
                            name = response.name,
                            parentId = response.parentId,
                            size = response.size,
                            contentType = response.contentType
                        )
                    }
                }
                .mapLeft {
                    Error.GeneralError
                }
        }
    }

    override suspend fun downloadItem(itemId: String): Either<Error, ByteArray> {
        return withContext(dispatcher) {
            fileNetworkService.downloadItem(
                auth = credentials,
                itemId = itemId
            )
                .map {
                    it.byteStream().readBytes()
                }
                .mapLeft {
                    Error.GeneralError
                }
        }
    }

    companion object {
        // Hard-coded for simplicity
        private val credentials = Credentials.basic("noel", "foobar")
    }
}