package ch04

import ch04.Arith
import ch04.Term._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ArithSpec extends AnyWordSpec with Matchers {
  val arith: Arith = Arith()
  "isNumericValue" should {
    "return `true` if give it `Zero`" in {
      arith.isNumericValue(Zero) should be(true)
    }
    "return `true` if give it `Succ(Zero)`" in {
      arith.isNumericValue(Succ(Zero)) should be(true)
    }
    "return `false` if give it `Succ(True)`" in {
      arith.isNumericValue(Succ(True)) should be(false)
    }
  }
  "isValuse" should {
    "return `true` if give it `True`" in {
      arith.isValue(True) should be(true)
    }
    "return `true` if give it `False`" in {
      arith.isValue(False) should be(true)
    }
    "return `true` if give it `Succ(Zero)`" in {
      arith.isValue(Succ(Zero)) should be(true)
    }
    "return `false` if give it `Succ(True)`" in {
      arith.isValue(Succ(True)) should be(false)
    }
  }
  "eval1" should {
    "return `t2` if give it `If(True, t2, t3)`" in {
      arith.eval1(If(True, True, False)) should be(True)
    }
    "return `t3` if give it `If(False, t2, t3)`" in {
      arith.eval1(If(False, True, False)) should be(False)
    }
    "return `If(t1', t2, t3)` if give it `If(t1, t2, t3)`" in {
      val t = If(True, True, False)
      val t1 = arith.eval1(t)
      arith.eval1(If(t, True, False)) should be(If(t1, True, False))
    }
    "return `Succ(t1')` if give it `Succ(t1)`" in {
      arith.eval1(Succ(If(True, True, False))) should be(Succ(True))
    }
    "return `Zero` if give it `Pred(Zero)`" in {
      arith.eval1(Pred(Zero)) should be(Zero)
    }
    "return `nv1` if give it `Pred(Succ(nv1))` at nv1 is numeric value" in {
      arith.eval1(Pred(Succ(Zero))) should be(Zero)
    }
    "return `Pred(t1')` if give it `Pred(t1)`" in {
      arith.eval1(Pred(Pred(Zero))) should be(Pred(Zero))
    }
    "return `True` if give it `IsZero(Zero)`" in {
      arith.eval1(IsZero(Zero)) should be(True)
    }
    "return `False` if give it `IsZero(Succ(nv1))` at nv1 is numeric value" in {
      arith.eval1(IsZero(Succ(Zero))) should be(False)
    }
    "return `IsZero(t1')` if give it `IsZero(t1)`" in {
      arith.eval1(IsZero(Pred(Zero))) should be(IsZero(Zero))
    }
    "throw `NoRuleApplies` if rule is not applicable" in {
      a[NoRuleApplies] should be thrownBy {
        arith.eval1(Succ(Zero))
      }
    }
  }
  "eval" should {
    "return `Succ(Zero)` if give it `If(False, Zero, Succ(Zero))`" in {
      arith.eval(If(False, Zero, Succ(Zero))) should be(Succ(Zero))
    }
    "return `True` if give it `IsZero(Pred(Succ(Zero)))`" in {
      arith.eval(IsZero(Pred(Succ(Zero)))) should be(True)
    }
    "return `True` if give it `If(True, True, If(False, False, False))`" in {
      arith.eval(If(True, True, If(False, False, False))) should be(True)
    }
  }
}
