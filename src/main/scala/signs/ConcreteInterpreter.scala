package signs

enum Expr:
  case Num(n: Int)
  case Minus(left: Expr, right: Expr)


enum Sign:
  case Positive
  case Negative
  case Zero
  case Bottom
  case Top

def eval(expr: Expr): Expr = expr match
  case Expr.Num(n) => Expr.Num(n)
  case Expr.Minus(left, right) => (eval(left), eval(right)) match
    case (Expr.Num(l), Expr.Num(r)) => Expr.Num(l - r)
    // TODO: how to mark this as unreachable?
    case _ => ???

// write some tests for the eval fn
// write a basic abstract interpreter to get the sign
  // how do I capture machine semantics - overflow/underflow
  // backward/forward semantics?
  // locations??
// write another one to get the intervals





