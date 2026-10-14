package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.HistoricalData
import com.example.data.UserPreferencesRepository
import com.example.data.UserState
import com.example.data.models.AvatarType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppScreen(val label: String, val iconEmoji: String) {
    WELCOME("Inicio", "🏛️"),
    MODULE_1("M1: Bases", "⚖️"),
    MODULE_2("M2: Civilizaciones", "📜"),
    MODULE_3("M3: Simuladores", "🧮"),
    EVALUATION("Evaluación", "⭐"),
    BADGES("Insignias", "👑")
}

data class AbacusState(
    // 3 columns: Hundreds (100), Tens (10), Units (1)
    // For each column: upperBeadActive (Boolean, value 5), lowerBeadsActive (Int 0..4, value 1 each)
    val hundredsUpper: Boolean = false,
    val hundredsLower: Int = 0,
    val tensUpper: Boolean = false,
    val tensLower: Int = 0,
    val unitsUpper: Boolean = false,
    val unitsLower: Int = 0
) {
    val totalValue: Int
        get() {
            val hundreds = (if (hundredsUpper) 5 else 0) + hundredsLower
            val tens = (if (tensUpper) 5 else 0) + tensLower
            val units = (if (unitsUpper) 5 else 0) + unitsLower
            return hundreds * 100 + tens * 10 + units
        }
}

data class NepohualtzitzinState(
    // 3 rods for vigesimal: Rod 0 (20^0 = 1), Rod 1 (20^1 = 20), Rod 2 (20^2 = 400)
    // On each rod: upperBeadsActive (0..3, each worth 5 * rodValue), lowerBeadsActive (0..4, each worth 1 * rodValue)
    // Max per rod: 3*5 + 4*1 = 19
    val rod0Upper: Int = 0, // 0..3
    val rod0Lower: Int = 0, // 0..4
    val rod1Upper: Int = 0, // 0..3
    val rod1Lower: Int = 0, // 0..4
    val rod2Upper: Int = 0, // 0..3
    val rod2Lower: Int = 0  // 0..4
) {
    val rod0Value: Int get() = (rod0Upper * 5 + rod0Lower) * 1
    val rod1Value: Int get() = (rod1Upper * 5 + rod1Lower) * 20
    val rod2Value: Int get() = (rod2Upper * 5 + rod2Lower) * 400
    val totalValue: Int get() = rod0Value + rod1Value + rod2Value
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = UserPreferencesRepository(application.applicationContext)

    val userState: StateFlow<UserState> = repo.userState

    private val _currentScreen = MutableStateFlow(AppScreen.WELCOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    // Module 1 Classification Game state: CardId -> "ADITIVO" or "POSICIONAL"
    private val _classificationSelections = MutableStateFlow<Map<String, String>>(emptyMap())
    val classificationSelections: StateFlow<Map<String, String>> = _classificationSelections.asStateFlow()

    private val _classificationFeedback = MutableStateFlow<String?>(null)
    val classificationFeedback: StateFlow<String?> = _classificationFeedback.asStateFlow()

    private val _classificationScore = MutableStateFlow<Int?>(null)
    val classificationScore: StateFlow<Int?> = _classificationScore.asStateFlow()

    // Module 2 State
    private val _selectedCivIndex = MutableStateFlow(0)
    val selectedCivIndex: StateFlow<Int> = _selectedCivIndex.asStateFlow()

    private val _numberToConvert = MutableStateFlow(45)
    val numberToConvert: StateFlow<Int> = _numberToConvert.asStateFlow()

    private val _romanMathStep = MutableStateFlow(0)
    val romanMathStep: StateFlow<Int> = _romanMathStep.asStateFlow()

    // Module 3 Abacus & Nepohualtzitzin State
    private val _abacusState = MutableStateFlow(AbacusState())
    val abacusState: StateFlow<AbacusState> = _abacusState.asStateFlow()

    private val _nepoState = MutableStateFlow(NepohualtzitzinState())
    val nepoState: StateFlow<NepohualtzitzinState> = _nepoState.asStateFlow()

    private val _targetCalculation = MutableStateFlow(45)
    val targetCalculation: StateFlow<Int> = _targetCalculation.asStateFlow()

    // Module 4 Quiz State
    private val _quizAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quizAnswers: StateFlow<Map<Int, Int>> = _quizAnswers.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    private val _quizCurrentIndex = MutableStateFlow(0)
    val quizCurrentIndex: StateFlow<Int> = _quizCurrentIndex.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun updateProfile(name: String, avatar: AvatarType) {
        repo.updateProfile(name, avatar)
    }

    // Module 1 actions
    fun selectClassification(cardId: String, category: String) {
        val current = _classificationSelections.value.toMutableMap()
        current[cardId] = category
        _classificationSelections.value = current
        _classificationFeedback.value = null
    }

    fun verifyClassification() {
        var correct = 0
        val cards = HistoricalData.classificationCards
        cards.forEach { card ->
            val userChoice = _classificationSelections.value[card.id]
            val expected = if (card.isPositional) "POSICIONAL" else "ADITIVO"
            if (userChoice == expected) {
                correct++
            }
        }
        _classificationScore.value = correct
        if (correct == cards.size) {
            _classificationFeedback.value = "¡Excelente! Has clasificado perfectamente todas las características de los sistemas."
            repo.completeModule("m1", xpReward = 120)
            repo.unlockBadge("m1_complete")
        } else {
            _classificationFeedback.value = "Has acertado $correct de ${cards.size}. Revisa las tarjetas resaltadas para corregirlas."
            repo.addXp(correct * 10)
        }
    }

    fun resetClassification() {
        _classificationSelections.value = emptyMap()
        _classificationFeedback.value = null
        _classificationScore.value = null
    }

    // Module 2 actions
    fun selectCivilization(index: Int) {
        _selectedCivIndex.value = index
    }

    fun setNumberToConvert(num: Int) {
        val clamped = num.coerceIn(1, 3999)
        _numberToConvert.value = clamped
    }

    fun setRomanMathStep(step: Int) {
        _romanMathStep.value = step.coerceIn(0, 4)
        if (step == 4) {
            repo.unlockBadge("roman_challenge")
            repo.addXp(50)
            repo.completeModule("m2", xpReward = 150)
        }
    }

    // Module 3 Abacus actions
    fun toggleAbacusUpper(column: String) {
        when (column) {
            "hundreds" -> _abacusState.value = _abacusState.value.copy(hundredsUpper = !_abacusState.value.hundredsUpper)
            "tens" -> _abacusState.value = _abacusState.value.copy(tensUpper = !_abacusState.value.tensUpper)
            "units" -> _abacusState.value = _abacusState.value.copy(unitsUpper = !_abacusState.value.unitsUpper)
        }
        checkModule3Completion()
    }

    fun setAbacusLower(column: String, count: Int) {
        when (column) {
            "hundreds" -> _abacusState.value = _abacusState.value.copy(hundredsLower = count.coerceIn(0, 4))
            "tens" -> _abacusState.value = _abacusState.value.copy(tensLower = count.coerceIn(0, 4))
            "units" -> _abacusState.value = _abacusState.value.copy(unitsLower = count.coerceIn(0, 4))
        }
        checkModule3Completion()
    }

    fun resetAbacus() {
        _abacusState.value = AbacusState()
    }

    // Module 3 Nepohualtzitzin actions
    fun setNepoUpper(rod: Int, count: Int) {
        val clamped = count.coerceIn(0, 3)
        when (rod) {
            0 -> _nepoState.value = _nepoState.value.copy(rod0Upper = clamped)
            1 -> _nepoState.value = _nepoState.value.copy(rod1Upper = clamped)
            2 -> _nepoState.value = _nepoState.value.copy(rod2Upper = clamped)
        }
        checkModule3Completion()
    }

    fun setNepoLower(rod: Int, count: Int) {
        val clamped = count.coerceIn(0, 4)
        when (rod) {
            0 -> _nepoState.value = _nepoState.value.copy(rod0Lower = clamped)
            1 -> _nepoState.value = _nepoState.value.copy(rod1Lower = clamped)
            2 -> _nepoState.value = _nepoState.value.copy(rod2Lower = clamped)
        }
        checkModule3Completion()
    }

    fun resetNepo() {
        _nepoState.value = NepohualtzitzinState()
    }

    fun setTargetCalculation(target: Int) {
        _targetCalculation.value = target
    }

    private fun checkModule3Completion() {
        val target = _targetCalculation.value
        if (_abacusState.value.totalValue == target && _nepoState.value.totalValue == target) {
            repo.completeModule("m3", xpReward = 150)
            repo.unlockBadge("m3_complete")
        }
    }

    // Module 4 Quiz actions
    fun answerQuiz(questionId: Int, optionIndex: Int) {
        val current = _quizAnswers.value.toMutableMap()
        current[questionId] = optionIndex
        _quizAnswers.value = current
    }

    fun nextQuizQuestion() {
        if (_quizCurrentIndex.value < HistoricalData.quizQuestions.size - 1) {
            _quizCurrentIndex.value += 1
        }
    }

    fun previousQuizQuestion() {
        if (_quizCurrentIndex.value > 0) {
            _quizCurrentIndex.value -= 1
        }
    }

    fun jumpToQuizQuestion(index: Int) {
        _quizCurrentIndex.value = index.coerceIn(0, HistoricalData.quizQuestions.size - 1)
    }

    fun finishQuiz() {
        var score = 0
        HistoricalData.quizQuestions.forEach { q ->
            if (_quizAnswers.value[q.id] == q.correctIndex) {
                score++
            }
        }
        _quizFinished.value = true
        repo.saveQuizScore(score, HistoricalData.quizQuestions.size)
        repo.completeModule("m4", xpReward = 200)
    }

    fun restartQuiz() {
        _quizAnswers.value = emptyMap()
        _quizFinished.value = false
        _quizCurrentIndex.value = 0
    }
}
