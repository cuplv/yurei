package yurei.abs.sign

import yurei.impls.imp.{Expr, Val}

enum Sign:
  case Pos
  case Neg
  case Zero
  case Top
  case Bott

// TODO: how to add latex styled comment?
// v => v^
def beta(expr: Val): Sign =
  import Val.*
  expr match
    case Num(0) => Sign.Zero
    case Num(n) if n < 0 => Sign.Neg
    case _ => Sign.Pos

// v^ \in v
def gamma(sign: Sign): Val = ???

// v^1 - v^2 => v^
def minus(left: Sign, right: Sign): Sign =
  import Sign.*
  (left, right) match
    case (Bott, _) | (_, Bott) => Bott
    case (Neg, Zero) | (Neg, Pos) | (Zero, Pos) => Neg
    case (Pos, Zero) | (Pos, Neg) | (Zero, Neg)  => Pos
    case (Neg, Neg) | (Pos, Pos) => Top
    case (Zero, Zero) => Zero
    case (Top, _) | (_, Top) => Top

// e => v^
def eval(expr: Expr): Sign =
  import Expr.*
  expr match
    case V(v) => beta(v)
    case Minus(l, r) => (eval(l), eval(r)) match
      case (l: Sign, r: Sign) => minus(l, r)