import BinaryOp.{Add, Div, Mul, Sub}
import Expr.Const

class InterpreterSuite extends munit.FunSuite {
  test("2 + 2") {
    val expr = Expr.BinOp(Add(Const(2), Const(2)))
    val expected = Const(4)
    assertEquals(step(expr), expected)
  }

  test("((2 - 5) * (-1))/3") {
    val expr = Expr.BinOp(
      Div(
        Expr.BinOp(
          Mul(
            Expr.BinOp(
              Sub(
                Const(2),
                Const(5)
              )
            ),
            Const(-1)
          )
        ),
        Const(3)
      )
    )
    val expected = Const(1)
    assertEquals(eval(expr), expected)
  }
}
