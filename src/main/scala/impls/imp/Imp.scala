package impls.imp

import impls.imp.Expr.V

enum Val:
  case Num(n: Int)

enum Expr:
  case V(v: Val)
  case Minus(left: Expr, right: Expr)

def eval(expr: Expr): Expr = expr match
  case V(Val.Num(n)) => Expr.V(Val.Num(n))
  case Expr.Minus(left, right) => (eval(left), eval(right)) match
    case (V(Val.Num(l)), V(Val.Num(r))) => Expr.V(Val.Num(l - r))
    // TODO: how to mark this as unreachable?
    case _ => ???
