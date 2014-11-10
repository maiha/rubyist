package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

class AsableTest extends FunSpec with Matchers {
  describe("as") {
    it("import") {
      import sc.ala.rubyist.As._

      val a:AnyRef = Map("test" -> 12)
      val b = a.as[Map[String,Int]]
      b("test") should equal(12)
    }

    it("extends") {
      class Foo extends sc.ala.rubyist.Asable {
        val a:AnyRef = Map("test" -> 12)
        val b = a.as[Map[String,Int]]
        b("test") should equal(12)
      }
      new Foo
    }
  }
}
