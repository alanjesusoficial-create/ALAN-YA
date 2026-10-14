package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.models.AvatarType
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun WelcomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val userState by viewModel.userState.collectAsState()
    var isEditingProfile by remember { mutableStateOf(false) }
    var tempName by remember(userState.userName) { mutableStateOf(userState.userName) }
    var tempAvatar by remember(userState.avatar) { mutableStateOf(userState.avatar) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("welcome_screen_scroll"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Hero Image Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hero_banner),
                    contentDescription = "Civilizaciones antiguas contando",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                            )
                        )
                )

                // Title and Subtitle over Hero
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Surface(
                        color = AntiqueGold.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "PENSAMIENTO MATEMÁTICO II • PROGRESIÓN 2",
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "El Legado del Conteo",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "De las Civilizaciones Antiguas al Sistema Indoarábigo",
                        color = AntiqueGoldLight,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Subtitle message
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🏺", fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Descubre cómo la humanidad aprendió a contar, medir y calcular a través de la historia: desde marcas en huesos hasta la revolución de Fibonacci.",
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Gamified Profile Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("user_profile_card"),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(AntiqueGold.copy(alpha = 0.2f))
                                .border(2.dp, AntiqueGold, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = userState.avatar.symbol, fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = userState.userName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${userState.avatar.title} • ${userState.avatar.subtitle}",
                                fontSize = 12.sp,
                                color = TerracottaPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        IconButton(
                            onClick = { isEditingProfile = !isEditingProfile },
                            modifier = Modifier.testTag("edit_profile_btn")
                        ) {
                            Icon(
                                imageVector = if (isEditingProfile) Icons.Default.Close else Icons.Default.Edit,
                                contentDescription = "Editar Perfil",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    // Level and XP progress bar
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nivel ${userState.level}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = AntiqueGoldDark
                        )
                        Text(
                            text = "${userState.xp} XP acumulados",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { userState.levelProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = AntiqueGold,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    // Profile Editor (Toggled)
                    AnimatedVisibility(visible = isEditingProfile) {
                        Column(modifier = Modifier.padding(top = 16.dp)) {
                            OutlinedTextField(
                                value = tempName,
                                onValueChange = { tempName = it },
                                label = { Text("Nombre del Estudiante") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("name_input_field"),
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Elige tu Avatar de la Historia:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(AvatarType.values()) { avatar ->
                                    val isSelected = tempAvatar == avatar
                                    Card(
                                        modifier = Modifier
                                            .width(130.dp)
                                            .clickable { tempAvatar = avatar },
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (isSelected) AntiqueGold.copy(alpha = 0.25f)
                                            else MaterialTheme.colorScheme.surfaceVariant
                                        ),
                                        border = if (isSelected) BorderStroke(2.dp, AntiqueGold) else null
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(10.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = avatar.symbol, fontSize = 28.sp)
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = avatar.title,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    viewModel.updateProfile(tempName, tempAvatar)
                                    isEditingProfile = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("save_profile_btn"),
                                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary)
                            ) {
                                Text("Guardar Cambios")
                            }
                        }
                    }
                }
            }
        }

        // Quick Badges Preview
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tus Insignias de Conquista",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                TextButton(onClick = { viewModel.navigateTo(AppScreen.BADGES) }) {
                    Text("Ver Todas (${userState.unlockedBadgeIds.size})")
                }
            }
        }

        // Mission Map
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Mapa de Misiones de Aprendizaje",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Avanza por cada etapa de la Progresión 2 para desbloquear tu Certificado.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Mission 1
                MissionCard(
                    number = "1",
                    title = "El Desafío Inicial y la Necesidad de Contar",
                    subtitle = "Entorno, surgimiento de bases (5, 10, 20, 60) y clasificación: Sistemas Aditivos vs Posicionales.",
                    isCompleted = userState.completedModules.contains("m1"),
                    icon = "⚖️",
                    tag = "mission_1_btn",
                    onClick = { viewModel.navigateTo(AppScreen.MODULE_1) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Mission 2
                MissionCard(
                    number = "2",
                    title = "Viaje por las Civilizaciones y Sistemas",
                    subtitle = "Mesopotamia, Egipto, Roma, Mayas e Indoarábigo. Conversor interactivo y duelo: Suma Romana vs Arábiga.",
                    isCompleted = userState.completedModules.contains("m2"),
                    icon = "📜",
                    tag = "mission_2_btn",
                    onClick = { viewModel.navigateTo(AppScreen.MODULE_2) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Mission 3
                MissionCard(
                    number = "3",
                    title = "Herramientas Ancestrales de Cálculo",
                    subtitle = "Simulador dual: Ábaco decimal vs Nepohualtzitzin vigesimal maya. Representación interactiva de números.",
                    isCompleted = userState.completedModules.contains("m3"),
                    icon = "🧮",
                    tag = "mission_3_btn",
                    onClick = { viewModel.navigateTo(AppScreen.MODULE_3) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Mission 4: Evaluation
                MissionCard(
                    number = "⭐",
                    title = "Evaluación Final y Certificado de Logro",
                    subtitle = "8 reactivos clave de la Progresión 2, retroalimentación detallada e insignia oficial descargable.",
                    isCompleted = userState.completedModules.contains("m4"),
                    icon = "👑",
                    tag = "mission_4_btn",
                    highlight = true,
                    onClick = { viewModel.navigateTo(AppScreen.EVALUATION) }
                )
            }
        }

        // Big CTA Button: Iniciar Aventura
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.navigateTo(AppScreen.MODULE_1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
                    .testTag("start_adventure_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Iniciar Aventura Histórica",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MissionCard(
    number: String,
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    icon: String,
    tag: String,
    highlight: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag(tag),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (highlight) AntiqueGold.copy(alpha = 0.15f)
            else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = if (highlight || isCompleted) 2.dp else 1.dp,
            color = if (isCompleted) TerracottaPrimary else if (highlight) AntiqueGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        if (isCompleted) TerracottaPrimary
                        else if (highlight) AntiqueGoldDark
                        else MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completado",
                        tint = Color.White
                    )
                } else {
                    Text(
                        text = icon,
                        fontSize = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Módulo $number",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (highlight) AntiqueGoldDark else TerracottaPrimary
                    )
                    if (isCompleted) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            color = TerracottaLight.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "¡Completado!",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = TerracottaDark,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
