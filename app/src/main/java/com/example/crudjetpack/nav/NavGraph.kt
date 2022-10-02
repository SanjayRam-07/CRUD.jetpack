package com.example.crudjetpack.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crudjetpack.screen.AddDataScreen
import com.example.crudjetpack.screen.GetDataScreen
import com.example.crudjetpack.screen.MainScreen
import com.example.crudjetpack.util.SharedViewModel

@Composable
fun NavGraph(
    navController:NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ){
        //main screen
        composable(
            Screens.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        //get data screen
        composable(
            Screens.GetDataScreen.route
        ) {
            GetDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

        //add data screen
        composable(
            Screens.AddDataScreen.route
        ) {
            AddDataScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }

    }

}