package com.shoppe.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shoppe.android.navigation.Screen
import com.shoppe.android.screens.CreateAccountPage
import com.shoppe.android.screens.LoginScreen
import com.shoppe.android.screens.PasswordPage
import com.shoppe.android.screens.ShopPage
import com.shoppe.android.screens.HelloCardPage
import com.shoppe.android.screens.NewPasswordPage
import com.shoppe.android.screens.PasswordRecoveryCodePage
import com.shoppe.android.screens.PasswordRecoveryPage
import com.shoppe.android.screens.StartPage
import com.shoppe.android.ui.theme.ShoppeAndroidTwoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set transparent status bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ShoppeAndroidTwoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Start.route
                    ) {
                        composable(Screen.Start.route) {
                            StartPage(
                                onNavigateToCreateAccount = {
                                    navController.navigate(Screen.CreateAccount.route)
                                },
                                onNavigateToLogin = {
                                    navController.navigate(Screen.Login.route)
                                }
                            )
                        }
                        composable(Screen.CreateAccount.route) {
                            CreateAccountPage(
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
                            )
                        }
                        composable(Screen.Login.route) {
                            LoginScreen(
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToPassword = {
                                    navController.navigate(Screen.Password.route)
                                }
                            )
                        }
                        composable(Screen.Password.route) {
                            PasswordPage(
                                onNavigateBack = { navController.popBackStack() },
                                onLoginSuccess = {
                                    // Navigate to hello card (onboarding) page after successful login
                                    navController.navigate(Screen.HelloCard.route)
                                },
                                onForgotPassword = {
                                    navController.navigate(Screen.PasswordRecovery.route)
                                }
                            )
                        }
                        composable(Screen.PasswordRecovery.route) {
                            PasswordRecoveryPage(
                                onNavigateBack = { navController.popBackStack() },
                                onRecoveryMethodSelected = { method ->
                                    // Navigate to code verification page
                                    navController.navigate(Screen.PasswordRecoveryCode.route)
                                }
                            )
                        }
                        composable(Screen.PasswordRecoveryCode.route) {
                            PasswordRecoveryCodePage(
                                onNavigateBack = { navController.popBackStack() },
                                onCodeVerified = {
                                    // Navigate to new password page
                                    navController.navigate(Screen.NewPassword.route)
                                },
                                onResendCode = {
                                    // TODO: Implement resend code logic
                                }
                            )
                        }
                        composable(Screen.NewPassword.route) {
                            NewPasswordPage(
                                onNavigateBack = { navController.popBackStack() },
                                onPasswordResetSuccess = {
                                    // TODO: Navigate to login or home page after successful reset
                                }
                            )
                        }
                        composable(Screen.HelloCard.route) {
                            HelloCardPage(
                                onNavigateToShop = {
                                    // Navigate to shop page after onboarding complete
                                    navController.navigate(Screen.Shop.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Screen.Shop.route) {
                            ShopPage()
                        }
                    }
                }
            }
        }
    }
}
