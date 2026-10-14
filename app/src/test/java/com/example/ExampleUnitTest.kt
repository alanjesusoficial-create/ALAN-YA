package com.example

import com.example.data.HistoricalData
import com.example.ui.AbacusState
import com.example.ui.NepohualtzitzinState
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testRomanConversion() {
    assertEquals("IV", HistoricalData.toRoman(4))
    assertEquals("XLV", HistoricalData.toRoman(45))
    assertEquals("CCLXVI", HistoricalData.toRoman(266))
    assertEquals("CXXXIV", HistoricalData.toRoman(134))
    assertEquals("CD", HistoricalData.toRoman(400))
  }

  @Test
  fun testMayaConversion() {
    val levels45 = HistoricalData.toMaya(45)
    // 45 in Maya: Level 1 (x20) = 2, Level 0 (x1) = 5
    assertEquals(2, levels45.size)
    assertEquals(2, levels45[0].valueInLevel) // 2 * 20 = 40
    assertEquals(5, levels45[1].valueInLevel) // 5 * 1 = 5
  }

  @Test
  fun testAbacusStateCalculation() {
    // 45 = 4 tens (lower=4) + 5 units (upper=true)
    val state = AbacusState(
      tensLower = 4,
      unitsUpper = true
    )
    assertEquals(45, state.totalValue)
  }

  @Test
  fun testNepohualtzitzinCalculation() {
    // 45 = 2 on Rod 1 (x20 = 40) + 1 upper (value 5) on Rod 0 (x1 = 5)
    val state = NepohualtzitzinState(
      rod1Lower = 2,
      rod0Upper = 1
    )
    assertEquals(45, state.totalValue)
  }
}
