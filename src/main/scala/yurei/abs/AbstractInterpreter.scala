package yurei.abs

trait Abs[A, C, V]:
  def beta(value: V): A
//  def gamma(concrete: A): C
  def minus(left: A, right: A): A
  def eval(value: C): A

def step_forward[A, C, V](statement: C, abs_dom: Abs[A,C, V]): A = abs_dom.eval(statement)
  

 
