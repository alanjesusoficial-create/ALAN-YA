package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun Module3Screen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val abacusState by viewModel.abacusState.collectAsState()
    val nepoState by viewModel.nepoState.collectAsState()
    val targetNumber by viewModel.targetCalculation.collectAsState()

    var simulatorTab by remember { mutableStateOf(0) } // 0 = Ábaco Decimal, 1 = Nepohualtzitzin

    val isAbacusMatch = abacusState.totalValue == targetNumber
    val isNepoMatch = nepoState.totalValue == targetNumber

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("module_3_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Module Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = AntiqueGoldDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "MÓDULO 3",
                                color = AntiqueGoldDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Instrumentos de Cómputo Histórico",
                            color = Color(0xFFFFEEB8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Herramientas Ancestrales de Cálculo",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Experimenta cómo calculaban matemáticos del viejo mundo y astrónomos mayas con el Ábaco Decimal y el Nepohualtzitzin vigesimal.",
                        color = DesertSandLight,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Conceptual Comparison Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Comparativa Conceptual de los Instrumentos",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Ábaco Column
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                color = LapisLazuli.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Ábaco Decimal (Base 10)",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LapisLazuli,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "• Base: Potencias de 10 (1, 10, 100...).\n• Cuentas superiores: valen 5.\n• Cuentas inferiores: valen 1 (hasta 4).\n• Valor máximo por varilla: 9.",
                                fontSize = 11.sp,
                                lineHeight = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        // Nepohualtzitzin Column
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                color = TerracottaPrimary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Nepohualtzitzin (Base 20)",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaDark,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "• Base: Vigesimal mesoamericano (1, 20, 400...).\n• 3 cuentas superiores de valor 5 (5x3=15).\n• 4 cuentas inferiores de valor 1 (1x4=4).\n• Valor máximo por varilla: 19.",
                                fontSize = 11.sp,
                                lineHeight = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Target Challenge Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.5.dp, AntiqueGold)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "🎯 Reto Activo de Simulación:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = AntiqueGoldDark
                            )
                            Text(
                                text = "Representa el número: $targetNumber",
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp,
                                color = TerracottaPrimary
                            )
                        }

                        // Target Presets
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            listOf(45, 128, 73).forEach { num ->
                                FilterChip(
                                    selected = targetNumber == num,
                                    onClick = { viewModel.setTargetCalculation(num) },
                                    label = { Text(num.toString(), fontSize = 11.sp) }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StatusIndicatorPill(
                            label = "Ábaco Decimal",
                            current = abacusState.totalValue,
                            target = targetNumber,
                            isSuccess = isAbacusMatch,
                            modifier = Modifier.weight(1f)
                        )
                        StatusIndicatorPill(
                            label = "Nepohualtzitzin",
                            current = nepoState.totalValue,
                            target = targetNumber,
                            isSuccess = isNepoMatch,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (isAbacusMatch && isNepoMatch) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "🎉 ¡Felicidades! Has representado el número $targetNumber con éxito en ambos instrumentos.",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // Simulator Tab Switcher
        item {
            TabRow(
                selectedTabIndex = simulatorTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = TerracottaPrimary
            ) {
                Tab(
                    selected = simulatorTab == 0,
                    onClick = { simulatorTab = 0 },
                    text = { Text("🧮 1. Ábaco Decimal (Base 10)", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                )
                Tab(
                    selected = simulatorTab == 1,
                    onClick = { simulatorTab = 1 },
                    text = { Text("🌽 2. Nepohualtzitzin (Base 20)", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                )
            }
        }

        // Active Simulator View
        item {
            if (simulatorTab == 0) {
                // Decimal Abacus
                DecimalAbacusInteractiveView(
                    state = abacusState,
                    onToggleUpper = { col -> viewModel.toggleAbacusUpper(col) },
                    onSetLower = { col, count -> viewModel.setAbacusLower(col, count) },
                    onReset = { viewModel.resetAbacus() }
                )
            } else {
                // Nepohualtzitzin
                NepohualtzitzinInteractiveView(
                    state = nepoState,
                    onSetUpper = { rod, count -> viewModel.setNepoUpper(rod, count) },
                    onSetLower = { rod, count -> viewModel.setNepoLower(rod, count) },
                    onReset = { viewModel.resetNepo() }
                )
            }
        }

        // Navigation CTA
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.navigateTo(AppScreen.EVALUATION) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("go_to_evaluation_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Avanzar a la Evaluación Final ➔",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun StatusIndicatorPill(
    label: String,
    current: Int,
    target: Int,
    isSuccess: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = if (isSuccess) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (isSuccess) Color(0xFF4CAF50) else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Medium)
            Text(
                text = "$current / $target",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSuccess) Color(0xFF2E7D32) else TerracottaPrimary
            )
            Text(
                text = if (isSuccess) "✓ Correcto" else "Ajusta cuentas",
                fontSize = 10.sp,
                color = if (isSuccess) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DecimalAbacusInteractiveView(
    state: com.example.ui.AbacusState,
    onToggleUpper: (String) -> Unit,
    onSetLower: (String, Int) -> Unit,
    onReset: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, LapisLazuli)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Ábaco Soroban / Decimal", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(text = "Toca las cuentas para activarlas hacia el travesaño central.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = onReset) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reiniciar Ábaco")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3 Columns: Hundreds, Tens, Units
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3E2723), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AbacusColumnVisual(
                    colName = "Centenas (x100)",
                    isUpperActive = state.hundredsUpper,
                    lowerActiveCount = state.hundredsLower,
                    onUpperClick = { onToggleUpper("hundreds") },
                    onLowerClick = { count -> onSetLower("hundreds", count) }
                )
                AbacusColumnVisual(
                    colName = "Decenas (x10)",
                    isUpperActive = state.tensUpper,
                    lowerActiveCount = state.tensLower,
                    onUpperClick = { onToggleUpper("tens") },
                    onLowerClick = { count -> onSetLower("tens", count) }
                )
                AbacusColumnVisual(
                    colName = "Unidades (x1)",
                    isUpperActive = state.unitsUpper,
                    lowerActiveCount = state.unitsLower,
                    onUpperClick = { onToggleUpper("units") },
                    onLowerClick = { count -> onSetLower("units", count) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                color = LapisLazuli.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Valor Total del Ábaco:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${state.totalValue}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = LapisLazuli
                    )
                }
            }
        }
    }
}

@Composable
fun AbacusColumnVisual(
    colName: String,
    isUpperActive: Boolean,
    lowerActiveCount: Int,
    onUpperClick: () -> Unit,
    onLowerClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(90.dp)
    ) {
        Text(text = colName, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(6.dp))

        // Upper Bead (Value 5)
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clickable { onUpperClick() },
            shape = CircleShape,
            color = if (isUpperActive) AntiqueGold else Color(0xFF6D4C41),
            border = BorderStroke(2.dp, if (isUpperActive) Color.White else Color(0xFF8D6E63))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "5", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

        // Beam / Separator
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(4.dp)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Lower Beads (1 each, up to 4)
        for (i in 1..4) {
            val isActive = i <= lowerActiveCount
            Surface(
                modifier = Modifier
                    .size(34.dp)
                    .padding(vertical = 2.dp)
                    .clickable {
                        // If already active at this level, clicking it steps down, else sets to i
                        if (lowerActiveCount == i) onLowerClick(i - 1) else onLowerClick(i)
                    },
                shape = CircleShape,
                color = if (isActive) TerracottaPrimary else Color(0xFF6D4C41),
                border = BorderStroke(1.5.dp, if (isActive) Color.White else Color(0xFF8D6E63))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "1", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        val colSum = (if (isUpperActive) 5 else 0) + lowerActiveCount
        Text(text = "= $colSum", color = Color.Yellow, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun NepohualtzitzinInteractiveView(
    state: com.example.ui.NepohualtzitzinState,
    onSetUpper: (Int, Int) -> Unit,
    onSetLower: (Int, Int) -> Unit,
    onReset: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, TerracottaPrimary)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Nepohualtzitzin Mesoamericano", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(text = "Base 20 (Vigesimal). Cuentas de barro/maíz: 3 superiores de 5 y 4 inferiores de 1.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = onReset) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reiniciar Nepohualtzitzin")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3 Rods: 20^2 (400), 20^1 (20), 20^0 (1)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2B1D15), RoundedCornerShape(8.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NepoRodVisual(
                    rodName = "Nivel 3 (x400)",
                    upperActive = state.rod2Upper,
                    lowerActive = state.rod2Lower,
                    rodValue = state.rod2Value,
                    onUpper = { count -> onSetUpper(2, count) },
                    onLower = { count -> onSetLower(2, count) }
                )
                NepoRodVisual(
                    rodName = "Nivel 2 (x20)",
                    upperActive = state.rod1Upper,
                    lowerActive = state.rod1Lower,
                    rodValue = state.rod1Value,
                    onUpper = { count -> onSetUpper(1, count) },
                    onLower = { count -> onSetLower(1, count) }
                )
                NepoRodVisual(
                    rodName = "Nivel 1 (x1)",
                    upperActive = state.rod0Upper,
                    lowerActive = state.rod0Lower,
                    rodValue = state.rod0Value,
                    onUpper = { count -> onSetUpper(0, count) },
                    onLower = { count -> onSetLower(0, count) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                color = TerracottaPrimary.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Valor Total Vigesimal:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${state.totalValue}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = TerracottaPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun NepoRodVisual(
    rodName: String,
    upperActive: Int, // 0..3 (each 5)
    lowerActive: Int, // 0..4 (each 1)
    rodValue: Int,
    onUpper: (Int) -> Unit,
    onLower: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(96.dp)
    ) {
        Text(text = rodName, color = AntiqueGoldLight, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(6.dp))

        // 3 Upper Beads (Value 5 each)
        for (i in 1..3) {
            val isActive = i <= upperActive
            Surface(
                modifier = Modifier
                    .size(28.dp)
                    .padding(vertical = 1.dp)
                    .clickable {
                        if (upperActive == i) onUpper(i - 1) else onUpper(i)
                    },
                shape = CircleShape,
                color = if (isActive) Color(0xFF00796B) else Color(0xFF4E342E), // Jade tone for Maya
                border = BorderStroke(1.dp, if (isActive) Color.White else Color(0xFF6D4C41))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "5", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }
        }

        // Beam
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(76.dp)
                .height(4.dp)
                .background(AntiqueGold)
        )
        Spacer(modifier = Modifier.height(4.dp))

        // 4 Lower Beads (Value 1 each)
        for (i in 1..4) {
            val isActive = i <= lowerActive
            Surface(
                modifier = Modifier
                    .size(28.dp)
                    .padding(vertical = 1.dp)
                    .clickable {
                        if (lowerActive == i) onLowerClick(i, lowerActive, onLower)
                    },
                shape = CircleShape,
                color = if (isActive) Color(0xFFD84315) else Color(0xFF4E342E), // Terracotta clay
                border = BorderStroke(1.dp, if (isActive) Color.White else Color(0xFF6D4C41))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "1", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Varilla: $rodValue", color = Color.Yellow, fontWeight = FontWeight.Bold, fontSize = 10.sp)
    }
}

private fun onLowerClick(i: Int, current: Int, callback: (Int) -> Unit) {
    if (current == i) callback(i - 1) else callback(i)
}
