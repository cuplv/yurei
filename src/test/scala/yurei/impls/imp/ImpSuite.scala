package yurei.impls.imp

import yurei.impls.imp.Expr.*
import yurei.impls.imp.Val.*

class ImpSuite extends munit.FunSuite {

  test("2 - 2") {
    val expr = Minus(V(Num(2)), V(Num(2)))
    val expected = V(Num(0))
    assertEquals(eval(expr), expected)
  }

}

class AbsSignSuite extends munit.FunSuite {
  import yurei.abs.sign.*

  // pos - zero => pos
  test("abs(2 - 0)") {
    val expr = Minus(V(Num(2)), V(Num(0)))
    val expected = Sign.Pos
    assertEquals(eval(expr), expected)
  }

  // neg - pos => neg
  test("abs(-2 - 3") {
    val expr = Minus(V(Num(-2)), V(Num(3)))
    val expected = Sign.Neg
    assertEquals(eval(expr), expected)
  }

  // pos - pos => T
  test("abs(2 - 2)") {
    val expr = Minus(V(Num(2)), V(Num(2)))
    val expected = Sign.Top
    assertEquals(eval(expr), expected)
  }

  // FIXME: how to capture underflow?
  test("abs(MIN - 1)") {
    val expr = Minus(V(Num(Int.MinValue)), V(Num(1)))
    val expected = Sign.Pos
    assertEquals(eval(expr), expected)
  }
}

class AbsIntervalSuite extends munit.FunSuite {
  import yurei.abs.interval.*
  import yurei.abs.interval.Interval.*

  test("1 -> [1,1]") {
    val expr = V(Num(1))
    val expected = Interval.UpperLower(1, 1)
    assertEquals(eval(expr), expected)
  }

  test("1 - 1 -> [0,0]") {
    val expr = Minus(V(Num(1)), V(Num(1)))
    val expected = Interval.UpperLower(0, 0)
    assertEquals(eval(expr), expected)
  }

  test("[1, inf] - [3, inf] -> [-2, inf]" ) {
    val a = GreatestLower(1)
    val b = GreatestLower(3)
    assertEquals(minus(a, b), GreatestLower(-2))
  }

  test("[inf, -2] - [inf, 5] -> [inf, -7]") {
    val a = LeastUpper(-2)
    val b = LeastUpper(5)
    assertEquals(minus(a, b), LeastUpper(-2 - 5))
  }

  test("[-inf, 3] - [5, inf]") {
    val a = LeastUpper(3)
    val b = GreatestLower(5)
    assertEquals(minus(a, b), Top)
  }
}