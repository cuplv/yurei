package yurei.abs

trait AbsDomain
trait Abs[A <: AbsDomain, C, V]:
  def beta(value: V): A
//  def gamma(concrete: A): C
  def minus(left: A, right: A): A
  def eval(value: C): A

def step_forward[A <: AbsDomain, C, V](
    statement: C,
    abs_doms: List[Abs[A, C, V]]
): List[A] =
  abs_doms.map(abs_dom => abs_dom.eval(statement))
