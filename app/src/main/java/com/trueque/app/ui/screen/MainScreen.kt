package com.trueque.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trueque.app.ui.viewmodel.SimpleAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToServiceDetail: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    val viewModel: SimpleAuthViewModel = hiltViewModel()
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                    label = { Text("Buscar") },
                    selected = selectedTab == 1,
                    onClick = { 
                        selectedTab = 1
                        onNavigateToSearch()
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Chat, contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = selectedTab == 2,
                    onClick = { /* Navigate to chat list */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") },
                    selected = selectedTab == 3,
                    onClick = { 
                        selectedTab = 3
                        onNavigateToProfile()
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> HomeScreen(
                    onNavigateToServiceDetail = onNavigateToServiceDetail,
                    onNavigateToProfile = onNavigateToProfile,
                    onNavigateToSearch = onNavigateToSearch
                )
                1 -> SearchScreen(
                    onNavigateBack = { selectedTab = 0 },
                    onNavigateToServiceDetail = onNavigateToServiceDetail
                )
                2 -> ChatListScreen(
                    onNavigateBack = { selectedTab = 0 }
                )
                3 -> ProfileScreen(
                    onNavigateBack = { selectedTab = 0 }
                )
            }
        }
    }
}
