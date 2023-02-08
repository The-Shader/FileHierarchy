package com.fireblade.filehierarchy

import app.cash.turbine.test
import arrow.core.Either
import com.fireblade.filehierarchy.home.presentation.ErrorStatus
import com.fireblade.filehierarchy.home.presentation.HomeViewIntent
import com.fireblade.filehierarchy.home.presentation.HomeViewModel
import com.fireblade.repository.IFileExplorerService
import com.fireblade.repository.model.CurrentUser
import com.fireblade.repository.model.Error
import com.fireblade.repository.model.ItemInfo
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTests {

    private val fileExplorerService: IFileExplorerService = mockk()
    private val subject: HomeViewModel = HomeViewModel(
        fileExplorerService = fileExplorerService
    )

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load current user`() {
        runTest {
            val rootItemInfo: ItemInfo = mockk {
                every { id } returns "id"
                every { isDir } returns true
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns null
                every { size } returns null
                every { contentType } returns null
            }
            val currentUser: CurrentUser = mockk {
                every { firstName } returns "firstName"
                every { lastName } returns "lastName"
                every { rootItem } returns rootItemInfo
            }

            coEvery { fileExplorerService.getCurrentUser() } returns Either.Right(currentUser)

            subject.viewState.test {
                subject.onIntent(HomeViewIntent.LoadCurrentUser)
                val initial = awaitItem()
                val final = awaitItem()
                coVerify(exactly = 1) { fileExplorerService.getCurrentUser() }
                assert(initial.navigationHistory.isEmpty())
                assert(final.navigationHistory.isNotEmpty() && final.errorStatus == ErrorStatus.NONE)
            }
        }
    }

    @Test
    fun `fail loading current user`() {
        runTest {
            coEvery { fileExplorerService.getCurrentUser() } returns Either.Left(Error.GeneralError)
            subject.viewState.test {
                subject.onIntent(HomeViewIntent.LoadCurrentUser)
                val initial = awaitItem()
                val final = awaitItem()
                coVerify(exactly = 1) { fileExplorerService.getCurrentUser() }
                assert(initial.navigationHistory.isEmpty())
                assert(final.navigationHistory.isEmpty() && final.errorStatus == ErrorStatus.USER_FAILED)
            }
        }
    }

    @Test
    fun `load folder items`() {
        runTest {
            val rootItemInfo: ItemInfo = mockk {
                every { id } returns "id"
                every { isDir } returns true
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns null
                every { size } returns null
                every { contentType } returns null
            }
            val folderItem: ItemInfo = mockk {
                every { id } returns "id2"
                every { isDir } returns true
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns rootItemInfo.id
                every { size } returns null
                every { contentType } returns null
            }

            coEvery { fileExplorerService.getItemInfo(any()) } returns Either.Right(listOf(folderItem))

            subject.viewState.test {
                subject.onIntent(HomeViewIntent.LoadChildItems(rootItemId = rootItemInfo.id))
                val initial = awaitItem()
                val final = awaitItem()
                coVerify(exactly = 1) { fileExplorerService.getItemInfo(any()) }
                assert(initial.navigationHistory.isEmpty() && initial.childItems.isEmpty())
                assert(
                    final.navigationHistory.isEmpty() &&
                            final.errorStatus == ErrorStatus.NONE &&
                            final.childItems.size == 1
                )
            }
        }
    }

    @Test
    fun `download image successfully`() {
        runTest {
            val rootItemInfo: ItemInfo = mockk {
                every { id } returns "id"
                every { isDir } returns true
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns null
                every { size } returns null
                every { contentType } returns null
            }
            val folderItem: ItemInfo = mockk {
                every { id } returns "id2"
                every { isDir } returns false
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns rootItemInfo.id
                every { size } returns 1
                every { contentType } returns "image"
            }
            val rawImage = ByteArray(1)

            coEvery { fileExplorerService.getItemInfo(any()) } returns Either.Right(listOf(folderItem))
            coEvery { fileExplorerService.downloadItem(any()) } returns Either.Right(rawImage)

            subject.viewState.test {
                subject.onIntent(HomeViewIntent.LoadChildItems(rootItemId = rootItemInfo.id))
                subject.onIntent(HomeViewIntent.ShowImage(imageId = folderItem.id))
                val initial = awaitItem()
                val final = awaitItem()
                coVerify(exactly = 1) { fileExplorerService.getItemInfo(any()) }
                coVerify(exactly = 1) { fileExplorerService.downloadItem(any()) }
                assert(initial.navigationHistory.isEmpty() && initial.childItems.isEmpty())
                assert(
                    final.navigationHistory.isEmpty() &&
                            final.errorStatus == ErrorStatus.NONE &&
                            final.childItems.size == 1 &&
                            final.rawImage.contentEquals(rawImage)
                )
            }
        }
    }

    @Test
    fun `fail to download image`() {
        runTest {
            val folderItem: ItemInfo = mockk {
                every { id } returns "id"
                every { isDir } returns false
                every { modificationDate } returns "modificationDate"
                every { name } returns "name"
                every { parentId } returns "parentId"
                every { size } returns 1
                every { contentType } returns "image"
            }

            coEvery { fileExplorerService.downloadItem(any()) } returns Either.Left(Error.GeneralError)

            subject.viewState.test {
                subject.onIntent(HomeViewIntent.ShowImage(imageId = folderItem.id))
                val initial = awaitItem()
                val final = awaitItem()
                coVerify(exactly = 1) { fileExplorerService.downloadItem(any()) }
                assert(initial.navigationHistory.isEmpty() && initial.childItems.isEmpty())
                assert(
                    final.navigationHistory.isEmpty() &&
                            final.errorStatus == ErrorStatus.IMAGE_DOWNLOAD_FAILED &&
                            final.childItems.isEmpty() &&
                            final.rawImage == null
                )
            }
        }
    }
}