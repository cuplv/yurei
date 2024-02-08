package impls.imp

enum Expr:
  case Num(n: Int)
  case Minus(left: Expr, right: Expr)

def eval(expr: Expr): Expr = expr match
  case Expr.Num(n) => Expr.Num(n)
  case Expr.Minus(left, right) => (eval(left), eval(right)) match
    case (Expr.Num(l), Expr.Num(r)) => Expr.Num(l - r)
    // TODO: how to mark this as unreachable?
    case _ => ???

