package com.trueque.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trueque.app.ui.screen.*

@Composable
fun SimpleTruequeNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToOtp = { email ->
                    navController.navigate("otp/$email")
                }
            )
        }
        
        composable("otp/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpScreen(
                email = email,
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("main") {
            SimpleMainScreen(
                onNavigateToServiceDetail = { serviceId ->
                    navController.navigate("service_detail/$serviceId")
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToSearch = {
                    navController.navigate("search")
                }
            )
        }
        
        composable("service_detail/{serviceId}") { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            ServiceDetailScreen(
                serviceId = serviceId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToChat = { userId ->
                    navController.navigate("chat/$userId")
                },
                onNavigateToPurchase = { serviceId ->
                    navController.navigate("purchase/$serviceId")
                }
            )
        }
        
        composable("profile") {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("search") {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToServiceDetail = { serviceId ->
                    navController.navigate("service_detail/$serviceId")
                }
            )
        }
        
        composable("chat/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ChatScreen(
                userId = userId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("purchase/{serviceId}") { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            PurchaseScreen(
                serviceId = serviceId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSuccess = {
                    navController.navigate("main") {
                        popUpTo("main") { inclusive = true }
                    }
                }
            )
        }
    }
}
