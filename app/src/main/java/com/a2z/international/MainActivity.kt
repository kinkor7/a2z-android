package com.a2z.international

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.a2z.international.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2ZTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        DashboardScreen { route -> navController.navigate(route) }
                    }
                    composable("assistant") {
                        CareerAssistantScreen { navController.popBackStack() }
                    }
                    composable("jobs") {
                        // Placeholder for Jobs Screen
                        Text("Jobs Directory coming soon...")
                    }
                    composable("visa") {
                        // Placeholder for Visa Screen
                        Text("Visa Guidance coming soon...")
                    }
                    composable("about") {
                        // Placeholder for About Screen
                        Text("About A2Z International coming soon...")
                    }
                    composable("contact") {
                        // Placeholder for Contact Screen
                        Text("Contact Details coming soon...")
                    }
                }
            }
        }
    }
}
