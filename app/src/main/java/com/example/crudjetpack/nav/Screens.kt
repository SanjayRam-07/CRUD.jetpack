package com.example.crudjetpack.nav

sealed class Screens(val route:String) {
    object MainScreen:Screens("main_screen")
    object GetDataScreen:Screens("get_data_screen")
    object AddDataScreen:Screens("add_data_screen")
}
