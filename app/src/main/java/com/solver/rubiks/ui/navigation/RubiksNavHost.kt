package com.solver.rubiks.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solver.rubiks.ui.screen.HomeScreen

@Composable
fun RubiksNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.Home) {
        composable<Destinations.Home> {
            HomeScreen()
        }
    }
}
