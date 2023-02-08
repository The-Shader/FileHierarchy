package com.fireblade.filehierarchy.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fireblade.filehierarchy.R
import com.fireblade.filehierarchy.home.presentation.HomeViewState
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageViewScreen(sharedViewState: StateFlow<HomeViewState>) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlowLifecycleAware = remember(sharedViewState, lifecycleOwner) {
        sharedViewState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
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
                GlideImage(
                    model = state.rawImage,
                    contentDescription = "Full Screen Image",
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    contentScale = ContentScale.Fit
                ) {
                    it.thumbnail()
                        .placeholder(R.drawable.ic_image)
                        .centerCrop()
                }
            }
        }
    }
}