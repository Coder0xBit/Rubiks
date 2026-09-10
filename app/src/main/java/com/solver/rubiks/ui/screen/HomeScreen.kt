package com.solver.rubiks.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.solver.rubiks.ui.components.FilamentViewer

@Composable
fun HomeScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HomeScreenContent()
        }
    }
}

@Composable
private fun HomeScreenContent() {
    var loadingProgress by remember { mutableFloatStateOf(0f) }
    var isLoaded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        FilamentViewer(
            modifier = Modifier.fillMaxSize(),
            modelName = "FlightHelmet",
            skyBoxName = "venetian_crossroads_2k",
            onProgressUpdate = { progress -> loadingProgress = progress },
            onLoaded = { isLoaded = true }
        )

        if (!isLoaded) {
            CircularProgressIndicator(
                progress = { loadingProgress },
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}