package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.screens.*
import com.example.ui.theme.*

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(viewModel: MainViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val userState by viewModel.userState.collectAsState()

    // Handle Android system back button cleanly
    BackHandler(enabled = currentScreen != AppScreen.WELCOME) {
        viewModel.navigateTo(AppScreen.WELCOME)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (currentScreen) {
                                AppScreen.WELCOME -> "El Legado del Conteo"
                                AppScreen.MODULE_1 -> "Módulo 1: Bases"
                                AppScreen.MODULE_2 -> "Módulo 2: Civilizaciones"
                                AppScreen.MODULE_3 -> "Módulo 3: Simuladores"
                                AppScreen.EVALUATION -> "Evaluación Sumativa"
                                AppScreen.BADGES -> "Insignias de Logro"
                            },
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                },
                navigationIcon = {
                    if (currentScreen != AppScreen.WELCOME) {
                        IconButton(
                            onClick = { viewModel.navigateTo(AppScreen.WELCOME) },
                            modifier = Modifier.testTag("nav_back_btn")
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver al inicio")
                        }
                    }
                },
                actions = {
                    // Quick user XP pill
                    Surface(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        color = AntiqueGold.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AntiqueGold)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = userState.avatar.symbol, fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Nv.${userState.level} • ${userState.xp} XP",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("bottom_nav_bar")
            ) {
                val navItems = listOf(
                    AppScreen.WELCOME,
                    AppScreen.MODULE_1,
                    AppScreen.MODULE_2,
                    AppScreen.MODULE_3,
                    AppScreen.EVALUATION
                )

                navItems.forEach { screen ->
                    val isSelected = currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.navigateTo(screen) },
                        icon = {
                            Text(
                                text = screen.iconEmoji,
                                fontSize = 18.sp
                            )
                        },
                        label = {
                            Text(
                                text = screen.label,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TerracottaPrimary,
                            selectedTextColor = TerracottaPrimary,
                            indicatorColor = AntiqueGold.copy(alpha = 0.25f)
                        ),
                        modifier = Modifier.testTag("nav_item_${screen.name}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.WELCOME -> WelcomeScreen(viewModel = viewModel)
                AppScreen.MODULE_1 -> Module1Screen(viewModel = viewModel)
                AppScreen.MODULE_2 -> Module2Screen(viewModel = viewModel)
                AppScreen.MODULE_3 -> Module3Screen(viewModel = viewModel)
                AppScreen.EVALUATION -> EvaluationScreen(viewModel = viewModel)
                AppScreen.BADGES -> BadgesScreen(viewModel = viewModel)
            }
        }
    }
}
