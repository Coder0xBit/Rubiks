package com.solver.rubiks.common

import android.content.Context
import com.google.android.filament.utils.KTX1Loader
import com.google.android.filament.utils.ModelViewer
import java.nio.ByteBuffer

object RubiksUtils {
    fun readAsset(context: Context, assetName: String): ByteBuffer {
        val input = context.assets.open(assetName)
        val bytes = input.readBytes()
        input.close()
        val buffer = ByteBuffer.allocateDirect(bytes.size)
        buffer.put(bytes)
        buffer.rewind()
        return buffer
    }

    fun loadGlb(context: Context, modelViewer: ModelViewer, assetName: String) {
        val buffer = readAsset(context = context, assetName = "Model/$assetName/${assetName}.gltf")
        modelViewer.loadModelGltfAsync(buffer) { uri ->
            readAsset(context = context, assetName = "Model/$assetName/$uri")
        }
        modelViewer.transformToUnitCube()
    }

    fun loadEnvironment(context: Context, modelViewer: ModelViewer, ibl: String) {
        var buffer = readAsset(context = context, assetName = "Skybox/$ibl/${ibl}_ibl.ktx")
        KTX1Loader.createIndirectLight(modelViewer.engine, buffer).apply {
            indirectLight?.intensity = 50_000f
            modelViewer.scene.indirectLight = indirectLight
        }

        buffer = readAsset(context = context, assetName = "Skybox/$ibl/${ibl}_skybox.ktx")
        KTX1Loader.createSkybox(modelViewer.engine, buffer).apply {
            modelViewer.scene.skybox = this.skybox
        }
    }
}