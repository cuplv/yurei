package yurei.abs.sign

import yurei.abs.{Abs, AbsDomain}
import yurei.impls.imp.{Expr, Val}

enum Sign extends AbsDomain:
  case Pos
  case Neg
  case Zero
  case Top
  case Bott

given SignExpr: Abs[Sign, Expr, Val] with
  // v => v^
  override def beta(value: Val): Sign =
    import Val.*
    value match
      case Num(0)          => Sign.Zero
      case Num(n) if n < 0 => Sign.Neg
      case _               => Sign.Pos

    // v^1 - v^2 => v^
  override def minus(left: Sign, right: Sign): Sign =
    import Sign.*
    (left, right) match
      case (Bott, _) | (_, Bott)                  => Bott
      case (Neg, Zero) | (Neg, Pos) | (Zero, Pos) => Neg
      case (Pos, Zero) | (Pos, Neg) | (Zero, Neg) => Pos
      case (Neg, Neg) | (Pos, Pos)                => Top
      case (Zero, Zero)                           => Zero
      case (Top, _) | (_, Top)                    => Top

  // e => v^
  override def eval(expr: Expr): Sign =
    import Expr.*
    expr match
      case V(v) => beta(v)
      case Minus(l, r) =>
        (eval(l), eval(r)) match
          case (l: Sign, r: Sign) => minus(l, r)
