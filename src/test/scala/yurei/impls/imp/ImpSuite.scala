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
  import yurei.abs.step_forward
  import yurei.abs.sign.SignExpr

  // pos - zero => pos
  test("abs(2 - 0)") {
    val expr = Minus(V(Num(2)), V(Num(0)))
    val expected = Sign.Pos
    assertEquals(step_forward(expr, SignExpr), expected)
  }

  // neg - pos => neg
  test("abs(-2 - 3") {
    val expr = Minus(V(Num(-2)), V(Num(3)))
    val expected = Sign.Neg
    assertEquals(step_forward(expr, SignExpr), expected)
  }

  // pos - pos => T
  test("abs(2 - 2)") {
    val expr = Minus(V(Num(2)), V(Num(2)))
    val expected = Sign.Top
    assertEquals(step_forward(expr, SignExpr), expected)
  }

  // FIXME: how to capture underflow?
  test("abs(MIN - 1)") {
    val expr = Minus(V(Num(Int.MinValue)), V(Num(1)))
    val expected = Sign.Pos
    assertEquals(step_forward(expr, SignExpr), expected)
  }
}

class AbsIntervalSuite extends munit.FunSuite {
  import yurei.abs.interval.*
  import yurei.abs.interval.Interval.*
  import yurei.abs.interval.IntervalExp
  import yurei.abs.step_forward

  test("1 -> [1,1]") {
    val expr = V(Num(1))
    val expected = Interval.UpperLower(1, 1)
    assertEquals(step_forward(expr, IntervalExp), expected)
  }

  test("1 - 1 -> [0,0]") {
    val expr = Minus(V(Num(1)), V(Num(1)))
    val expected = Interval.UpperLower(0, 0)
    assertEquals(step_forward(expr, IntervalExp), expected)
  }
}