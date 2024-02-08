package impls.imp

import impls.imp.Expr.*
import impls.imp.Val.*


class ImpSuite extends munit.FunSuite {

  test("2 - 2") {
    val expr = Minus(V(Num(2)), V(Num(2)))
    val expected = V(Num(0))
    assertEquals(eval(expr), expected)
  }

}
