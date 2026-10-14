package com.example.data.models

enum class AvatarType(
    val title: String,
    val subtitle: String,
    val description: String,
    val symbol: String
) {
    ARCHEOLOGIST(
        title = "Arqueólogo Matemático",
        subtitle = "Explorador del Tiempo",
        description = "Descifra inscripciones en papiro y arcilla en templos y tumbas.",
        symbol = "🧭"
    ),
    MAYA_SCRIBE(
        title = "Escriba Maya",
        subtitle = "Guardián del Cero",
        description = "Domina el calendario de cuenta larga, el caracol y la base vigesimal.",
        symbol = "🐚"
    ),
    MEDIEVAL_MERCHANT(
        title = "Mercader Medieval",
        subtitle = "Pionero de Fibonacci",
        description = "Aplica el Liber Abaci en las rutas de comercio mediterráneo.",
        symbol = "⚖️"
    ),
    ROMAN_CENTURION(
        title = "Centurión Romano",
        subtitle = "Estratega Imperial",
        description = "Registra legiones, tributos y monumentos con números de piedra.",
        symbol = "🏛️"
    )
}

data class BadgeItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val unlocked: Boolean = false
)

data class ClassificationCard(
    val id: String,
    val title: String,
    val description: String,
    val isPositional: Boolean, // true = Posicional, false = Aditivo
    val systemExample: String
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val civilizationContext: String
)

data class CivilisationInfo(
    val id: String,
    val name: String,
    val period: String,
    val baseNumber: Int,
    val systemType: String, // "Aditivo" o "Posicional"
    val summary: String,
    val keySymbols: List<Pair<String, String>>, // Símbolo -> Significado
    val historicalImpact: String,
    val iconEmoji: String
)
