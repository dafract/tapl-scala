package ch04

class Arith {
  def isNumericValue(t: Term): Boolean = t match {
    case Term.Zero => true
    case Term.Succ(t) => isNumericValue(t)
    case _ => false
  }

  def isValue(t: Term): Boolean = t match {
    case Term.True => true
    case Term.False => true
    case t if isNumericValue(t) => true
    case _ => false
  }
}
