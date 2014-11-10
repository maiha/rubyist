package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

class TapSpec extends FunSpec with Matchers {
  import Tap._

  describe("tap") {
    it("should yield function, and then return self") {
      var count = 0
      val x = "x".tap{ count = 1 }
      assert(count === 1)
      assert(x === "x")
    }
  }

  describe("tap(arg => )") {
    it("should yield function(self), and then return self") {
      var count = 0
      val foo = "foo".tap{ count += _.size }
      assert(count === 3)
      assert(foo === "foo")
    }
  }
}
