package com.solver.rubiks.ui.components

import android.annotation.SuppressLint
import android.graphics.PixelFormat
import android.view.Choreographer
import android.view.SurfaceView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.filament.utils.ModelViewer
import com.solver.rubiks.common.RubiksUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("ClickableViewAccessibility")
@Composable
fun FilamentViewer(
    modifier: Modifier = Modifier,
    modelName: String = "",
    skyBoxName: String = "",
    onProgressUpdate: (Float) -> Unit,
    onLoaded: () -> Unit
) {
    val context = LocalContext.current
    val surfaceView = remember {
        SurfaceView(context).apply {
            setZOrderOnTop(true)
            holder.setFormat(PixelFormat.TRANSLUCENT)
        }
    }

    val modelViewer = remember { ModelViewer(surfaceView) }

    LaunchedEffect(modelName) {
        withContext(Dispatchers.IO) {
            RubiksUtils.loadGlb(
                context = context,
                modelViewer = modelViewer,
                assetName = modelName
            )

            RubiksUtils.loadEnvironment(
                context = context,
                modelViewer = modelViewer,
                ibl = skyBoxName
            )
        }

        while (modelViewer.progress < 1f) {
            onProgressUpdate(modelViewer.progress)
            delay(50.milliseconds)
        }
        onProgressUpdate(1f)
        onLoaded()
    }

    val choreographer = remember { Choreographer.getInstance() }

    val frameCallback = remember {
        object : Choreographer.FrameCallback {
            override fun doFrame(currentTime: Long) {
                choreographer.postFrameCallback(this)
                modelViewer.render(currentTime)
            }
        }
    }

    DisposableEffect(Unit) {
        choreographer.postFrameCallback(frameCallback)
        onDispose {
            choreographer.removeFrameCallback(frameCallback)
        }
    }

    AndroidView(
        factory = {
            surfaceView.apply {
                setOnTouchListener(modelViewer)
            }
        },
        modifier = modifier.fillMaxSize()
    )
}
