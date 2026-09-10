package com.solver.rubiks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.filament.utils.Utils
import com.solver.rubiks.ui.navigation.RubiksNavHost
import com.solver.rubiks.ui.theme.RubiksTheme

class MainActivity : ComponentActivity() {
    companion object {
        init {
            Utils.init()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RubiksTheme {
                RubiksNavHost()
            }
        }
    }
}
