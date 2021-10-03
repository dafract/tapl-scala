import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class Test1 extends AnyFlatSpec with Matchers {
  "A msg" should "return `I was compiled by Scala 3. :)`" in {
    msg should be("I was compiled by Scala 3. :)")
  }
}
