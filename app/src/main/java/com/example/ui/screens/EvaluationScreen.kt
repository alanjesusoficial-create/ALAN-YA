package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HistoricalData
import com.example.data.models.QuizQuestion
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EvaluationScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val userState by viewModel.userState.collectAsState()
    val answers by viewModel.quizAnswers.collectAsState()
    val isFinished by viewModel.quizFinished.collectAsState()
    val currentIndex by viewModel.quizCurrentIndex.collectAsState()

    val questions = HistoricalData.quizQuestions
    val currentQuestion = questions[currentIndex]

    var showCertificateDialog by remember { mutableStateOf(false) }

    if (isFinished) {
        // Results & Feedback View
        QuizResultsView(
            viewModel = viewModel,
            userState = userState,
            questions = questions,
            answers = answers,
            onShowCertificate = { showCertificateDialog = true },
            modifier = modifier
        )
    } else {
        // Active Quiz View
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("evaluation_quiz_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
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
                                    text = "EVALUACIÓN SUMATIVA",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Progresión 2 (Págs. 47-62)",
                                color = AntiqueGoldLight,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Gran Evaluación de los Sistemas de Conteo",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Demuestra tu dominio sobre aditivos vs posicionales, limitaciones de Roma, rol del cero, la revolución de Fibonacci y el Nepohualtzitzin.",
                            color = DesertSandMedium,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Progress bar
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pregunta ${currentIndex + 1} de ${questions.size}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${answers.size} respondidas",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = AntiqueGoldDark
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { (currentIndex + 1f) / questions.size },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = TerracottaPrimary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            // Question Box
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = currentQuestion.civilizationContext,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TerracottaPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = currentQuestion.question,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Options
                        currentQuestion.options.forEachIndexed { optIndex, optionText ->
                            val isSelected = answers[currentQuestion.id] == optIndex
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { viewModel.answerQuiz(currentQuestion.id, optIndex) }
                                    .testTag("quiz_opt_${currentQuestion.id}_$optIndex"),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) AntiqueGold.copy(alpha = 0.2f)
                                    else MaterialTheme.colorScheme.surfaceVariant
                                ),
                                border = if (isSelected) BorderStroke(2.dp, AntiqueGoldDark)
                                else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { viewModel.answerQuiz(currentQuestion.id, optIndex) },
                                        colors = RadioButtonDefaults.colors(selectedColor = AntiqueGoldDark)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = optionText,
                                        fontSize = 13.sp,
                                        lineHeight = 17.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Navigation between questions
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { viewModel.previousQuizQuestion() },
                        enabled = currentIndex > 0
                    ) {
                        Text("◀ Anterior")
                    }

                    if (currentIndex < questions.size - 1) {
                        Button(
                            onClick = { viewModel.nextQuizQuestion() },
                            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                            modifier = Modifier.testTag("quiz_next_btn")
                        ) {
                            Text("Siguiente ▶")
                        }
                    } else {
                        Button(
                            onClick = { viewModel.finishQuiz() },
                            enabled = answers.size >= questions.size,
                            colors = ButtonDefaults.buttonColors(containerColor = AntiqueGoldDark),
                            modifier = Modifier.testTag("quiz_finish_btn")
                        ) {
                            Text("Entregar Evaluación ⭐")
                        }
                    }
                }
            }
        }
    }

    // Official Certificate Dialog
    if (showCertificateDialog) {
        CertificateDialog(
            userState = userState,
            totalQuestions = questions.size,
            onDismiss = { showCertificateDialog = false }
        )
    }
}

@Composable
fun QuizResultsView(
    viewModel: MainViewModel,
    userState: com.example.data.UserState,
    questions: List<QuizQuestion>,
    answers: Map<Int, Int>,
    onShowCertificate: () -> Unit,
    modifier: Modifier = Modifier
) {
    var correctCount = 0
    questions.forEach { q ->
        if (answers[q.id] == q.correctIndex) correctCount++
    }
    val percentage = (correctCount * 100) / questions.size
    val stars = when {
        percentage >= 90 -> 3
        percentage >= 70 -> 2
        else -> 1
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("quiz_results_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Results Celebration Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = TerracottaDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (stars) {
                            3 -> "⭐⭐⭐"
                            2 -> "⭐⭐"
                            else -> "⭐"
                        },
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (percentage >= 70) "¡Acreditación Sobresaliente!" else "Buen Intento: A Repasar",
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Calificación: $correctCount / ${questions.size} aciertos ($percentage%)",
                        color = AntiqueGoldLight,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (percentage >= 70)
                            "Has demostrado un profundo entendimiento de la Progresión 2 de Pensamiento Matemático II."
                        else
                            "Te recomendamos revisar el desglose didáctico abajo para reforzar los conceptos clave.",
                        color = DesertSandLight,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Action Buttons: Certificate, Retry, Back to modules
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onShowCertificate,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("view_certificate_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = AntiqueGoldDark)
                ) {
                    Icon(Icons.Default.CardMembership, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Certificado Oficial", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { viewModel.restartQuiz() },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Reintentar", fontSize = 12.sp)
                }
            }
        }

        item {
            Text(
                text = "Desglose y Retroalimentación Pedagógica:",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Explicación detallada de cada reactivo conforme a las páginas 47-62 del curso:",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Detailed Feedback for each Question
        itemsIndexed(questions) { idx, q ->
            val userAnswer = answers[q.id]
            val isCorrect = userAnswer == q.correctIndex

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isCorrect) Color(0xFFF1F8E9) else Color(0xFFFFEBEE)
                ),
                border = BorderStroke(
                    1.dp,
                    if (isCorrect) Color(0xFF4CAF50) else Color(0xFFE57373)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                            contentDescription = null,
                            tint = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Reactivo ${idx + 1} • ${q.civilizationContext}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = q.question,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    if (!isCorrect && userAnswer != null) {
                        Text(
                            text = "❌ Tu respuesta: ${q.options[userAnswer]}",
                            fontSize = 12.sp,
                            color = Color(0xFFB71C1C)
                        )
                    }
                    Text(
                        text = "✓ Respuesta correcta: ${q.options[q.correctIndex]}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "💡 Fundamento Teórico: ${q.explanation}",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }

        // Return to Mission Map
        item {
            Button(
                onClick = { viewModel.navigateTo(AppScreen.WELCOME) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Volver al Mapa de Misiones ➔", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CertificateDialog(
    userState: com.example.data.UserState,
    totalQuestions: Int,
    onDismiss: () -> Unit
) {
    val dateStr = remember {
        val sdf = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("es", "MX"))
        sdf.format(Date())
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary)
            ) {
                Text("Cerrar Certificado")
            }
        },
        title = null,
        text = {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = DesertSandLight),
                border = BorderStroke(3.dp, AntiqueGold)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "👑", fontSize = 36.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "CERTIFICADO DE LOGRO MATEMÁTICO",
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp,
                        color = TerracottaDark,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Pensamiento Matemático II • Progresión 2",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AntiqueGoldDark
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Se otorga con distinción académica a:",
                        fontSize = 11.sp,
                        color = AncientTextMuted
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userState.userName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = AncientTextDark
                    )
                    Text(
                        text = "${userState.avatar.symbol} ${userState.avatar.title}",
                        fontSize = 11.sp,
                        color = TerracottaPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Por haber completado con excelencia el estudio histórico de los sistemas de numeración: Egipto, Mesopotamia, Roma, América (Mayas) y la adopción universal del Sistema Indoarábigo y el Cero tras el Liber Abaci de Fibonacci.",
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Center,
                        color = AncientTextDark
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = AntiqueGold, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Fecha de Emisión:", fontSize = 9.sp, color = AncientTextMuted)
                            Text(dateStr, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Puntaje:", fontSize = 9.sp, color = AncientTextMuted)
                            Text("${userState.quizHighScore} / $totalQuestions Puntos", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TerracottaPrimary)
                        }
                    }
                }
            }
        }
    )
}
