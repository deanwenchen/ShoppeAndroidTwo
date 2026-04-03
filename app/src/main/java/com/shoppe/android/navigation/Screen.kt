package com.shoppe.android.navigation

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object CreateAccount : Screen("create_account")
    object Login : Screen("login")
    object Password : Screen("password")
    object PasswordRecovery : Screen("password_recovery")
    object PasswordRecoveryCode : Screen("password_recovery_code")
    object NewPassword : Screen("new_password")
    object HelloCard : Screen("hello_card")
    object Shop : Screen("shop")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: String) = "product/$productId"
    }
}
