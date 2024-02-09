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
