package org.rogervinas

class MyImpl {
  fun doSomething(
    a: Int,
    b: Int,
  ) = when {
    (a < 0 || b < 0) -> "Either A or B are negative"
    (a == 0 && b == 0) -> "Both A and B are zero"
    (a > b) -> "A is greater than B"
    (b > a) -> "B is greater than A"
    else -> "A and B are equal"
  }
}
