package yurei.impls.imp

enum Val:
  case Num(n: Int)

enum Expr:
  case V(v: Val)
  case Minus(left: Expr, right: Expr)

def eval(expr: Expr): Expr = expr match
  case Expr.V(Val.Num(n)) => Expr.V(Val.Num(n))
  case Expr.Minus(left, right) =>
    (eval(left), eval(right)) match
      case (Expr.V(Val.Num(l)), Expr.V(Val.Num(r))) => Expr.V(Val.Num(l - r))
      // TODO: how to mark this as unreachable?
      case _ => ???
