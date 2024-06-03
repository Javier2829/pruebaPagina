package com.javal.hilt_project.app.model

sealed class Routes(val route: String) {
    object loginScreen: Routes("loginScreen")
    object principalScreen: Routes("principalScreen")
    object resgistrarScreen: Routes("resgistrarScreen")
}