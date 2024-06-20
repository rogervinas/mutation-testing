package org.rogervinas

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MyImplTest {
  @ParameterizedTest
  @CsvSource(
    value = [
      "0, 0, Both A and B are zero",
      "-1, 0, Either A or B are negative",
      "0, -1, Either A or B are negative",
      "-1, -1, Either A or B are negative",
      "20, 13, A is greater than B",
      "11, 47, B is greater than A",
      "5, 5, A and B are equal",
      "1, 1, A and B are equal",
    ],
  )
  fun `should do something`(
    a: Int,
    b: Int,
    expectedResult: String,
  ) {
    assertThat(MyImpl().doSomething(a, b)).isEqualTo(expectedResult)
  }
}
