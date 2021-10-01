package ch04

import ch04.Term._
import ch04.NoRuleApplies

class Arith() {
  def isNumericValue(t: Term): Boolean = t match {
    case Zero => true
    case Succ(t) => isNumericValue(t)
    case _ => false
  }

  def isValue(t: Term): Boolean = t match {
    case True => true
    case False => true
    case t if isNumericValue(t) => true
    case _ => false
  }

  def eval1(t: Term): Term = t match {
    case If(True, t2: Term, t3: Term) => t2
    case If(False, t2: Term, t3: Term) => t3
    case If(t: Term, t2: Term, t3: Term) => {
      val t1 = eval1(t)
      If(t1, t2, t3)
    }
    case Succ(t) => {
      val t1 = eval1(t)
      Succ(t1)
    }
    case Pred(Zero) => Zero
    case Pred(Succ(nv)) if isNumericValue(nv) => nv
    case Pred(t) => {
      val t1 = eval1(t)
      Pred(t1)
    }
    case IsZero(Zero) => True
    case IsZero(Succ(nv)) if isNumericValue(nv) => False
    case IsZero(t) => {
      val t1 = eval1(t)
      IsZero(t1)
    }
    case _ => throw NoRuleApplies()
  }

  def eval(t: Term): Term =
    try {
      val t1 = eval1(t)
      eval(t1)
    } catch {
      case e: NoRuleApplies => t
    }
}
