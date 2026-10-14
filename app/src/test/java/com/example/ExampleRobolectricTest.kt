package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.HistoricalData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Legado del Conteo", appName)
  }

  @Test
  fun `verify historical civilizations and quiz data loaded`() {
    val civs = HistoricalData.civilizations
    assertEquals(5, civs.size)
    assertTrue(civs.any { it.id == "roma" })
    assertTrue(civs.any { it.id == "mayas" })

    val questions = HistoricalData.quizQuestions
    assertEquals(8, questions.size)
  }
}
