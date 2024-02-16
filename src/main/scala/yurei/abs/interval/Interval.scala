package yurei.abs.interval

import yurei.abs.Abs
import yurei.impls.imp.{Expr, Val}

enum Interval:
  case Top
  case GreatestLower(n: Int)
  case UpperLower(a: Int, b: Int)
  case LeastUpper(n : Int)
  case Bott
  
given IntervalExp: Abs[Interval, Expr, Val] with
  // v => v^
  override def beta(value: Val) : Interval =
    import Interval.*
    import Val.*
    value match
      case Num(n) => Interval.UpperLower(n, n)

  // v^ \in v
  def gamma(interval: Interval): Val = ???

  // v^1 - v^2 => v^
  override def minus(left: Interval, right: Interval): Interval =
    import Interval.*
    (left, right) match
      case (Top, _) | (_, Top) | (GreatestLower(_), LeastUpper(_)) | (LeastUpper(_), GreatestLower(_)) => Top
      case (GreatestLower(a), GreatestLower(b)) => GreatestLower(a - b)
      case (GreatestLower(a), UpperLower(b, _)) => GreatestLower(a - b)
      case (UpperLower(a, _), GreatestLower(b)) => GreatestLower(a - b)
      case (UpperLower(a1, b1), UpperLower(a2, b2)) => UpperLower(a1 - a2, b1 - b2)
      case (UpperLower(a1, b1), LeastUpper(b)) => LeastUpper(b1 - b)
      case (LeastUpper(b), UpperLower(_, b1)) => LeastUpper(b - b1)
      case (LeastUpper(a), LeastUpper(b)) => LeastUpper(a - b)
      case (Bott, _) | (_, Bott) => Bott
      
  // e => v^
  override def eval(expr: Expr): Interval =
    import Expr.*
    expr match
      case Expr.V(v) => beta(v)
      case Expr.Minus(l, r) => (eval(l), eval(r)) match
        case (l, r) => minus(l, r)
