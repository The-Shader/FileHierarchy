package com.fireblade.filehierarchy.home.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fireblade.filehierarchy.R
import com.fireblade.filehierarchy.home.presentation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = NavigationDestination.HOME_VIEW.toString()) {
                composable(NavigationDestination.HOME_VIEW.toString()) {
                    HomeScreen {
                        navController.navigate(
                            NavigationDestination.IMAGE_VIEW.toString()
                        )
                    }
                }
                composable(NavigationDestination.IMAGE_VIEW.toString()) {
                    ImageViewScreen(
                        sharedViewState = viewModel.viewState
                    )
                }
            }
        }
        viewModel.onIntent(HomeViewIntent.LoadCurrentUser)
    }

    @Composable
    private fun HomeScreen(onItemClick: () -> Unit) {
        val lifecycleOwner = LocalLifecycleOwner.current
        val stateFlowLifecycleAware = remember(viewModel.viewState, lifecycleOwner) {
            viewModel.viewState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }
        val viewState: HomeViewState? by stateFlowLifecycleAware.collectAsState(initial = null)
        viewState?.let { state ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.navigationHistory.size > 1) {
                        TitleWithBackButton(navigationHistory = state.navigationHistory)
                    } else {
                        SimpleTextTitle()
                    }
                    ItemList(listItems = state.childItems, onItemClick = onItemClick)
                }
            }
        }
    }

    @Composable
    private fun SimpleTextTitle() {
        Text(
            text = getString(R.string.home_title),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
                lineHeight = 24.sp
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun TitleWithBackButton(navigationHistory: List<FileViewItem>) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            GlideImage(
                model = R.drawable.ic_arrow_back,
                contentDescription = "File icon",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        viewModel.onIntent(
                            HomeViewIntent.NavigateBackToFolder(navigationHistory)
                        )
                    },
                contentScale = ContentScale.Fit
            )
            Text(
                text = getString(R.string.home_title),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    lineHeight = 24.sp
                ),
                modifier = Modifier.padding(start = 96.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }

    @Composable
    private fun ItemList(listItems: List<FileViewItem>, onItemClick: () -> Unit) {
        LazyColumn {
            itemsIndexed(listItems) { index, listItem ->
                if (index == 0) {
                    Divider()
                }
                FileItemRow(listItem = listItem, onItemClick = onItemClick)
                Divider()
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun FileItemRow(listItem: FileViewItem, onItemClick: () -> Unit) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    if (listItem.contentType == ContentType.IMAGE) {
                        viewModel.onIntent(HomeViewIntent.ShowImage(imageId = listItem.id))
                        onItemClick()
                    } else {
                        viewModel.onIntent(
                            HomeViewIntent.NavigateToFolder(listItem)
                        )
                    }
                }
        ) {
            GlideImage(
                model = when (listItem.contentType) {
                    ContentType.FOLDER -> R.drawable.ic_folder
                    ContentType.IMAGE -> R.drawable.ic_image
                    ContentType.OTHER -> R.drawable.ic_other_file
                },
                contentDescription = "File icon",
                modifier = Modifier
                    .padding(12.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = getString(
                    R.string.item_name,
                    listItem.name
                ),
                modifier = Modifier
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        end = 12.dp
                    )
            )
        }
    }
}