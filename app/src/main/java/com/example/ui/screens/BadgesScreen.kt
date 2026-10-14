package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserPreferencesRepository
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun BadgesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val userState by viewModel.userState.collectAsState()
    val allBadges = UserPreferencesRepository.ALL_BADGES

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("badges_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(AppScreen.WELCOME) }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = "Insignias de Logro Matemático",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Has desbloqueado ${userState.unlockedBadgeIds.size} de ${allBadges.size} reconocimientos",
                        fontSize = 12.sp,
                        color = AntiqueGoldDark,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        items(allBadges) { badge ->
            val isUnlocked = userState.unlockedBadgeIds.contains(badge.id)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                border = BorderStroke(
                    width = if (isUnlocked) 1.5.dp else 1.dp,
                    color = if (isUnlocked) AntiqueGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                if (isUnlocked) AntiqueGold.copy(alpha = 0.2f)
                                else Color.LightGray.copy(alpha = 0.3f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isUnlocked) {
                            Text(text = badge.icon, fontSize = 24.sp)
                        } else {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Bloqueado",
                                tint = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = badge.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                            )
                            if (isUnlocked) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = Color(0xFFE8F5E9),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "¡Desbloqueada!",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF2E7D32),
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = badge.description,
                            fontSize = 12.sp,
                            color = if (isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
