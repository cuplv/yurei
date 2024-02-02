import BinaryOp.{Add, Div, Mul, Sub}
import Expr.{BinOp, Const}

// TODO: Implement a stack machine for selecting instructions
enum BinaryOp:
  case Add(left: Expr, right: Expr)
  case Sub(left: Expr, right: Expr)
  case Mul(left: Expr, right: Expr)
  case Div(left: Expr, right: Expr)

enum Expr:
  case Const(num: Int)
  case BinOp(op: BinaryOp)


def step(expr: Expr): Expr = expr match
  case Const(_) => ???
  case BinOp(op: BinaryOp) => op match
    case Add(Expr.Const(left), Expr.Const(right)) => Expr.Const(left + right)
    case Add(Expr.Const(left), right) => BinOp(BinaryOp.Add(Expr.Const(left), step(right)))
    case Add(left, right) => BinOp(BinaryOp.Add(step(left), right))
    case Sub(Expr.Const(left), Expr.Const(right))
    => Expr.Const(left - right)
    case Sub(Expr.Const(left), right)
    => BinOp(BinaryOp.Sub(Expr.Const(left), step(right)))
    case Sub(left, right)
    => BinOp(BinaryOp.Sub(step(left), right))
    case Mul(Expr.Const(left), Expr.Const(right))
    => Expr.Const(left * right)
    case Mul(Expr.Const(left), right)
    => BinOp(BinaryOp.Mul(Expr.Const(left), step(right)))
    case Mul(left, right)
    => BinOp(BinaryOp.Mul(step(left), right))
    case Div(Expr.Const(left), Expr.Const(right))
    => Expr.Const(left / right)
    case Div(Expr.Const(left), right)
    => BinOp(BinaryOp.Div(Expr.Const(left), step(right)))
    case Div(left, right)
    => BinOp(BinaryOp.Div(step(left), right))

def eval(expr: Expr): Expr = expr match
  case Const(c) => Const(c)
  case BinOp(op: BinaryOp) => op match
    case Add(left, right) => (eval(left), eval(right)) match
      case (Const(left), Const(right)) => Const(left + right)
      case _ => ???
    case Sub(left, right) => (eval(left), eval(right)) match
      case (Const(left), Const(right)) => Const(left - right)
      case _ => ???
    case Mul(left, right) => (eval(left), eval(right)) match
      case (Const(left), Const(right)) => Const(left * right)
      case _ => ???
    case Div(left, right) => (eval(left), eval(right)) match
      case (Const(left), Const(right)) => Const(left / right)
      case _ => ???
