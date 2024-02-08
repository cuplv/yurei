package impls.imp

import impls.imp.Expr.{Minus, Num}

class ImpSuite extends munit.FunSuite {

  test("2 - 2") {
    val expr = Minus(Num(2), Num(2))
    val expected = Num(0)
    assertEquals(eval(expr), expected)
  }

}
