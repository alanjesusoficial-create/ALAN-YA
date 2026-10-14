package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.data.models.AvatarType
import com.example.data.models.BadgeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserState(
    val userName: String = "Estudiante",
    val avatar: AvatarType = AvatarType.ARCHEOLOGIST,
    val xp: Int = 0,
    val completedModules: Set<String> = emptySet(),
    val unlockedBadgeIds: Set<String> = emptySet(),
    val quizHighScore: Int = 0
) {
    val level: Int
        get() = (xp / 150) + 1

    val xpInCurrentLevel: Int
        get() = xp % 150

    val levelProgress: Float
        get() = (xp % 150) / 150f
}

class UserPreferencesRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("legado_conteo_prefs", Context.MODE_PRIVATE)

    private val _userState = MutableStateFlow(loadState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    private fun loadState(): UserState {
        val name = prefs.getString("user_name", "Estudiante de Bachillerato") ?: "Estudiante de Bachillerato"
        val avatarStr = prefs.getString("avatar", AvatarType.ARCHEOLOGIST.name) ?: AvatarType.ARCHEOLOGIST.name
        val avatar = try {
            AvatarType.valueOf(avatarStr)
        } catch (e: Exception) {
            AvatarType.ARCHEOLOGIST
        }
        val xp = prefs.getInt("xp", 50) // Initial welcome XP
        val modules = prefs.getStringSet("completed_modules", emptySet()) ?: emptySet()
        val badges = prefs.getStringSet("unlocked_badges", setOf("welcome_badge")) ?: setOf("welcome_badge")
        val highScore = prefs.getInt("quiz_high_score", 0)

        return UserState(
            userName = name,
            avatar = avatar,
            xp = xp,
            completedModules = modules,
            unlockedBadgeIds = badges,
            quizHighScore = highScore
        )
    }

    fun updateProfile(name: String, avatar: AvatarType) {
        prefs.edit()
            .putString("user_name", name)
            .putString("avatar", avatar.name)
            .apply()
        _userState.value = _userState.value.copy(userName = name, avatar = avatar)
    }

    fun addXp(points: Int) {
        val newXp = _userState.value.xp + points
        prefs.edit().putInt("xp", newXp).apply()
        _userState.value = _userState.value.copy(xp = newXp)
    }

    fun completeModule(moduleId: String, xpReward: Int = 100) {
        val newModules = _userState.value.completedModules + moduleId
        val newXp = _userState.value.xp + xpReward
        prefs.edit()
            .putStringSet("completed_modules", newModules)
            .putInt("xp", newXp)
            .apply()
        _userState.value = _userState.value.copy(
            completedModules = newModules,
            xp = newXp
        )
    }

    fun unlockBadge(badgeId: String) {
        val newBadges = _userState.value.unlockedBadgeIds + badgeId
        prefs.edit().putStringSet("unlocked_badges", newBadges).apply()
        _userState.value = _userState.value.copy(unlockedBadgeIds = newBadges)
    }

    fun saveQuizScore(score: Int, total: Int) {
        val currentBest = _userState.value.quizHighScore
        if (score > currentBest) {
            prefs.edit().putInt("quiz_high_score", score).apply()
            _userState.value = _userState.value.copy(quizHighScore = score)
        }
        val xpEarned = score * 25
        addXp(xpEarned)
        if (score >= total * 0.75) {
            unlockBadge("master_evaluator")
        }
        if (score == total) {
            unlockBadge("fibonacci_perfection")
        }
    }

    companion object {
        val ALL_BADGES = listOf(
            BadgeItem(
                id = "welcome_badge",
                title = "Iniciador del Saber",
                description = "Comenzó su viaje a través de la historia del conteo.",
                icon = "📜"
            ),
            BadgeItem(
                id = "m1_complete",
                title = "Arquitecto de Bases",
                description = "Dominó la diferencia entre sistemas aditivos y posicionales.",
                icon = "⚖️"
            ),
            BadgeItem(
                id = "m2_complete",
                title = "Políglota Matemático",
                description = "Exploró los sistemas de Mesopotamia, Egipto, Roma, Mayas e India.",
                icon = "🏛️"
            ),
            BadgeItem(
                id = "roman_challenge",
                title = "Superador de Roma",
                description = "Descubrió las limitaciones del cálculo romano frente al indoarábigo.",
                icon = "⚔️"
            ),
            BadgeItem(
                id = "m3_complete",
                title = "Calculador Ancestral",
                description = "Manejó con destreza el Ábaco decimal y el Nepohualtzitzin maya.",
                icon = "🧮"
            ),
            BadgeItem(
                id = "master_evaluator",
                title = "Sabio de la Progresión 2",
                description = "Aprobó la evaluación con honores y alto rendimiento.",
                icon = "⭐"
            ),
            BadgeItem(
                id = "fibonacci_perfection",
                title = "Heredero del Liber Abaci",
                description = "Alcanzó la puntuación perfecta demostrando el poder del valor posicional.",
                icon = "👑"
            )
        )
    }
}
