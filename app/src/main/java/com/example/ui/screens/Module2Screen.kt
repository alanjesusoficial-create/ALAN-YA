package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HistoricalData
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun Module2Screen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedIndex by viewModel.selectedCivIndex.collectAsState()
    val numberToConvert by viewModel.numberToConvert.collectAsState()
    val romanMathStep by viewModel.romanMathStep.collectAsState()

    val civs = HistoricalData.civilizations
    val currentCiv = civs[selectedIndex]

    var inputNumberText by remember(numberToConvert) { mutableStateOf(numberToConvert.toString()) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("module_2_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Module Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LapisLazuliDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = AntiqueGold,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "MÓDULO 2",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Evolución de los Sistemas Numéricos",
                            color = AntiqueGoldLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Viaje por las Civilizaciones y sus Sistemas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Explora las 5 grandes culturas matemáticas, traduce números a escrituras antiguas y descubre el impacto de la revolución de Fibonacci.",
                        color = DesertSandMedium,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Civilization Selector Tabs / Chips
        item {
            Text(
                text = "Selecciona una Civilización:",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(civs) { index, civ ->
                    val isSelected = selectedIndex == index
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.selectCivilization(index) },
                        label = {
                            Text(
                                text = "${civ.iconEmoji} ${civ.name.split(" ")[0]}",
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        modifier = Modifier.testTag("civ_tab_$index"),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TerracottaPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }

        // Detailed Civilization Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, AntiqueGold.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = currentCiv.iconEmoji, fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentCiv.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${currentCiv.period} • Base ${currentCiv.baseNumber}",
                                fontSize = 12.sp,
                                color = TerracottaPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Surface(
                            color = if (currentCiv.systemType.contains("Posicional")) LapisLazuli.copy(alpha = 0.15f)
                            else TerracottaPrimary.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (currentCiv.systemType.contains("Posicional")) "POSICIONAL" else "ADITIVO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (currentCiv.systemType.contains("Posicional")) LapisLazuli else TerracottaPrimary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = currentCiv.summary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Símbolos y Estructura:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = AntiqueGoldDark
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    currentCiv.keySymbols.forEach { (symbol, meaning) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.widthIn(min = 90.dp)
                            ) {
                                Text(
                                    text = symbol,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaDark,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = meaning,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = AntiqueGold.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                Icons.Default.Info,
                                contentDescription = null,
                                tint = AntiqueGoldDark,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = currentCiv.historicalImpact,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Interactive Translator Tool
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Traductor Matemático Ancestral",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Introduce una cantidad en números indoarábigos y observa cómo se representaba en las diversas culturas:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = inputNumberText,
                            onValueChange = { newText ->
                                inputNumberText = newText.filter { it.isDigit() }
                                val parsed = inputNumberText.toIntOrNull()
                                if (parsed != null && parsed in 1..3999) {
                                    viewModel.setNumberToConvert(parsed)
                                }
                            },
                            label = { Text("Número (1 - 3999)") },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("number_convert_input"),
                            singleLine = true
                        )

                        // Quick selector presets
                        listOf(45, 128, 266, 1492).forEach { preset ->
                            OutlinedButton(
                                onClick = {
                                    inputNumberText = preset.toString()
                                    viewModel.setNumberToConvert(preset)
                                },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.height(48.dp)
                            ) {
                                Text(preset.toString(), fontSize = 11.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Translations Display
                    CivilizationTranslatorsView(number = numberToConvert)
                }
            }
        }

        // Direct Comparative Challenge: Roman vs Indo-Arabic Math!
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, TerracottaPrimary)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "⚔️", fontSize = 26.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Reto Comparativo: Suma Romana vs Indoarábiga",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TerracottaPrimary
                            )
                            Text(
                                text = "¿Por qué el sistema romano requería un ábaco y el indoarábigo triunfó?",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Challenge comparison card
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Sistema Romano", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TerracottaDark)
                                Text("CCLXVI + CXXXIV", fontSize = 16.sp, fontWeight = FontWeight.Black)
                            }
                            Text("VS", fontSize = 16.sp, fontWeight = FontWeight.Black, color = AntiqueGoldDark)
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Sistema Indoarábigo", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LapisLazuli)
                                Text("266 + 134", fontSize = 16.sp, fontWeight = FontWeight.Black)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Stepper for solving
                    when (romanMathStep) {
                        0 -> {
                            Column {
                                Text(
                                    text = "Paso 1: Juntar todos los símbolos romanos",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "En números romanos no hay columnas posicionales con valor relativo. Primero desglosamos las sustracciones (IV = IIII) y agrupamos todas las letras:\nCCLXVI + CXXXIIII ➔ C C C L X X X X V I I I I I",
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                        1 -> {
                            Column {
                                Text(
                                    text = "Paso 2: Reagrupar unidades menores",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Tenemos cinco 'I' (I I I I I = V). Al reemplazar, nos queda:\nV + V = X.\nAhora sumamos esa nueva X a las decenas que ya teníamos (X X X X + X = XXXXX = L).",
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                        2 -> {
                            Column {
                                Text(
                                    text = "Paso 3: Reagrupar decenas a centenas",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Teníamos una L (50) más la nueva L (50): L + L = C (100).\nSumamos esa nueva C a las tres C que ya teníamos (C C C + C = CCCC = CD en notación sustractiva).\nResultado final: CD (400).",
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                        3 -> {
                            Column {
                                Text(
                                    text = "Paso 4: Comparación directa con el Sistema Indoarábigo",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Surface(
                                    color = Color(0xFFE8F5E9),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = "  1 1  (Acarreos)\n  2 6 6\n+ 1 3 4\n-------\n  4 0 0",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "¡Se resuelve en 3 segundos en columna gracias al valor posicional de las decenas y centenas y el cero como guardián de unidades!",
                                            fontSize = 12.sp,
                                            color = Color(0xFF1B5E20)
                                        )
                                    }
                                }
                            }
                        }
                        4 -> {
                            Surface(
                                color = AntiqueGold.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "💡 Reflexión Histórica (Páginas 47-62 Progresión 2):",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = AntiqueGoldDark
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "El sistema romano era excelente para registrar en lápidas monumentales o leyes sin alteración, pero era pésimo para calcular por escrito. Los comerciantes requerían obligatoriamente el ábaco manual romano.\n\nPor eso, cuando Fibonacci publicó en 1202 el 'Liber Abaci', los mercaderes europeos abandonaron progresivamente la numeración romana para adoptar las 9 cifras indias y el 'sifr' (cero), cimiento del comercio y la banca moderna.",
                                        fontSize = 12.sp,
                                        lineHeight = 17.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { viewModel.setRomanMathStep(romanMathStep - 1) },
                            enabled = romanMathStep > 0
                        ) {
                            Text("◀ Paso Anterior")
                        }

                        Button(
                            onClick = { viewModel.setRomanMathStep(romanMathStep + 1) },
                            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                            modifier = Modifier.testTag("next_step_roman_btn")
                        ) {
                            Text(if (romanMathStep < 4) "Siguiente Paso ▶" else "¡Reto Superado! +50 XP")
                        }
                    }
                }
            }
        }

        // Navigation CTA
        item {
            Button(
                onClick = { viewModel.navigateTo(AppScreen.MODULE_3) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("go_to_module_3_btn"),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Continuar al Módulo 3: Ábaco y Nepohualtzitzin ➔",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun CivilizationTranslatorsView(number: Int) {
    val roman = HistoricalData.toRoman(number)
    val mayaLevels = HistoricalData.toMaya(number)
    val egyptian = HistoricalData.toEgyptian(number)
    val babylonian = HistoricalData.toBabylonian(number)

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Roman Card
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🏛️ En Sistema Romano (Aditivo / Sustractivo):", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(text = roman, fontWeight = FontWeight.Black, fontSize = 16.sp, color = TerracottaPrimary)
                }
                Text(
                    text = "Regla: Letras con valor fijo sumadas (o restadas si van a la izquierda como IV=4 o XC=90).",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Maya Card (Vertical Positional)
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "🐚 En Sistema Maya (Posicional Vigesimal Vertical):", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text(
                    text = "Se lee de abajo hacia arriba en potencias de 20 (Nivel 1 = x1, Nivel 2 = x20, Nivel 3 = x400):",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))

                mayaLevels.forEach { level ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = AntiqueGold.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.width(105.dp)
                        ) {
                            Text(
                                text = "Nivel ${level.levelIndex + 1} (x${level.multiplier})",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AntiqueGoldDark,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        // Visual Representation of Dots and Bars or Shell
                        if (level.isZero) {
                            Text(text = "🐚 (Caracol / Cero: Ciclo completo)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Dots
                                repeat(level.dotsCount) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(TerracottaPrimary)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                                if (level.dotsCount > 0 && level.barsCount > 0) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                // Bars
                                repeat(level.barsCount) {
                                    Box(
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(5.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(LapisLazuli)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "= ${level.valueInLevel} (equivale a ${level.valueInLevel * level.multiplier})",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Egyptian Card
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "𓀀 En Jeroglíficos Egipcios (Aditivo Decimal):", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                val breakdown = buildString {
                    if (egyptian.thousands > 0) append("${egyptian.thousands}x Flor de loto (𓆸 1000) ")
                    if (egyptian.hundreds > 0) append("${egyptian.hundreds}x Cuerda enrollada (𓍢 100) ")
                    if (egyptian.tens > 0) append("${egyptian.tens}x Asa/talón (∩ 10) ")
                    if (egyptian.units > 0) append("${egyptian.units}x Trazos (| 1)")
                }
                Text(
                    text = breakdown.ifEmpty { "Cero no contemplado en el sistema formal de conteo" },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TerracottaDark
                )
            }
        }

        // Babylonian Card
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "🌾 En Sistema Babilónico (Posicional Sexagesimal):", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                babylonian.forEach { level ->
                    Text(
                        text = "Orden 60^${level.sexagesimalPower} (x${level.multiplier}): ${level.tensCount} decenas (<) y ${level.unitsCount} unidades (T) = ${level.value}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
