import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class AsableTest extends Spec with ShouldMatchers {
  describe("as") {
    describe("import") {
      import sc.ala.rubyist.As._

      val a:AnyRef = Map("test" -> 12)
      val b = a.as[Map[String,Int]]
      b("test") should equal(12)
    }

    describe("extends") {
      class Foo extends sc.ala.rubyist.Asable {
        val a:AnyRef = Map("test" -> 12)
        val b = a.as[Map[String,Int]]
        b("test") should equal(12)
      }
      new Foo
    }
  }
}
