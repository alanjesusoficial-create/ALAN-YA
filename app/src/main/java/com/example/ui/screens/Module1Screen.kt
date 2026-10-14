package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HistoricalData
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun Module1Screen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selections by viewModel.classificationSelections.collectAsState()
    val feedback by viewModel.classificationFeedback.collectAsState()
    val score by viewModel.classificationScore.collectAsState()
    val cards = HistoricalData.classificationCards

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("module_1_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = TerracottaDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = AntiqueGold,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "MÓDULO 1",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Orígenes del Pensamiento Matemático",
                            color = AntiqueGoldLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "El Desafío Inicial y la Necesidad de Contar",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Antes de los números escritos, los primeros seres humanos debieron inventar formas ingeniosas de llevar la cuenta de rebaños, días lunares y cosechas.",
                        color = DesertSandMedium,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Section 1: Entorno y Primeros Métodos
        item {
            Text(
                text = "1. Elementos del Entorno y Registro Primitivo",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PrimitiveItemCard(
                    emoji = "🦴",
                    title = "Hueso de Ishango",
                    subtitle = "Muescas talladas hace 20,000 años para contar ciclos lunares y múltiplos.",
                    modifier = Modifier.weight(1f)
                )
                PrimitiveItemCard(
                    emoji = "🪨",
                    title = "Guijarros (Cálculos)",
                    subtitle = "Del latín 'calculus' = piedrecita. Se usaban para contar cabras una a una.",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PrimitiveItemCard(
                    emoji = "🖐️",
                    title = "Dedos y Falanges",
                    subtitle = "La primera calculadora natural. Dio origen a las bases 5, 10, 12 y 60.",
                    modifier = Modifier.weight(1f)
                )
                PrimitiveItemCard(
                    emoji = "🧶",
                    title = "Nudos y Quipus",
                    subtitle = "Cuerdas con nudos para registrar cosechas y censos en civilizaciones andinas.",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Section 2: Surgimiento de las Bases
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "2. La Invención de la «Base» Numérica",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Al crecer las cantidades, contar de uno en uno era insostenible. Surgió el concepto de «agrupación» o base:",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BaseExplorationCard(
                    baseName = "Base 5 (Quinario)",
                    origin = "5 dedos de una mano.",
                    usage = "Pueblos recolectores y tribus de Oceanía y África.",
                    badgeColor = TerracottaPrimary
                )
                BaseExplorationCard(
                    baseName = "Base 10 (Decimal)",
                    origin = "10 dedos de ambas manos.",
                    usage = "Egipto, China, Roma y el actual sistema universal.",
                    badgeColor = LapisLazuli
                )
                BaseExplorationCard(
                    baseName = "Base 20 (Vigesimal)",
                    origin = "20 dedos de manos y pies juntos.",
                    usage = "Mayas, Olmecas y Aztecas en Mesoamérica.",
                    badgeColor = AntiqueGoldDark
                )
                BaseExplorationCard(
                    baseName = "Base 60 (Sexagesimal)",
                    origin = "12 falanges contadas con el pulgar x 5 dedos.",
                    usage = "Mesopotamia (Babilonia). Hoy viva en el reloj (60 min) y los 360° del círculo.",
                    badgeColor = TerracottaDark
                )
            }
        }

        // Section 3: Aditivos vs Posicionales
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "3. Clasificación: Sistemas Aditivos vs Posicionales",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Sistemas Aditivos",
                                fontWeight = FontWeight.Bold,
                                color = TerracottaPrimary,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "• Valor inmutable de cada signo.\n• Se suman independientemente del lugar.\n• Ejemplos: Egipto, Roma.\n• Gran limitación: Operaciones complejas sobre papel casi imposibles.",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Divider(
                            modifier = Modifier
                                .height(100.dp)
                                .width(1.dp)
                                .padding(horizontal = 8.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Sistemas Posicionales",
                                fontWeight = FontWeight.Bold,
                                color = LapisLazuli,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "• El valor depende de la columna/orden.\n• Potencias crecientes de la base.\n• Ejemplos: Babilonia, Maya, Indoarábigo.\n• CLAVE: Requieren OBLIGATORIAMENTE un símbolo para el CERO.",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Section 4: Minijuego Interactivo de Clasificación
        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "4. Minijuego: Clasifica las Características",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${selections.size}/${cards.size} clasificadas",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AntiqueGoldDark
                )
            }
            Text(
                text = "Toca cada tarjeta para asignarla a Sistema Aditivo o Posicional y pon a prueba tus conocimientos:",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Classification Cards List
        items(cards) { card ->
            val userChoice = selections[card.id]
            val isSubmitted = score != null
            val isCorrect = if (isSubmitted) {
                val expected = if (card.isPositional) "POSICIONAL" else "ADITIVO"
                userChoice == expected
            } else null

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("classification_card_${card.id}"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (isCorrect) {
                        true -> Color(0xFFE8F5E9)
                        false -> Color(0xFFFFEBEE)
                        null -> MaterialTheme.colorScheme.surfaceVariant
                    }
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = when (isCorrect) {
                        true -> Color(0xFF2E7D32)
                        false -> Color(0xFFC62828)
                        null -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    }
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = card.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = card.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = card.systemExample,
                        fontSize = 11.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Option Aditivo
                        FilterChip(
                            selected = userChoice == "ADITIVO",
                            onClick = { viewModel.selectClassification(card.id, "ADITIVO") },
                            label = { Text("Sistema Aditivo") },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_aditivo_${card.id}"),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = TerracottaPrimary,
                                selectedLabelColor = Color.White
                            )
                        )

                        // Option Posicional
                        FilterChip(
                            selected = userChoice == "POSICIONAL",
                            onClick = { viewModel.selectClassification(card.id, "POSICIONAL") },
                            label = { Text("Sistema Posicional") },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("btn_posicional_${card.id}"),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = LapisLazuli,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Action Buttons & Feedback
        item {
            AnimatedVisibility(visible = feedback != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (score == cards.size) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        1.dp,
                        if (score == cards.size) Color(0xFF4CAF50) else Color(0xFFFF9800)
                    )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = if (score == cards.size) "🏆" else "💡", fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = feedback ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.resetClassification() },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reiniciar")
                }

                Button(
                    onClick = { viewModel.verifyClassification() },
                    enabled = selections.size == cards.size,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("verify_classification_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = AntiqueGoldDark)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Comprobar")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.navigateTo(AppScreen.MODULE_2) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("go_to_module_2_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Continuar al Módulo 2: Civilizaciones ➔",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun PrimitiveItemCard(
    emoji: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = emoji, fontSize = 26.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
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
    }
}

@Composable
fun BaseExplorationCard(
    baseName: String,
    origin: String,
    usage: String,
    badgeColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(badgeColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FilterFrames,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = baseName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = badgeColor
                )
                Text(
                    text = "Origen: $origin",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Uso: $usage",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
