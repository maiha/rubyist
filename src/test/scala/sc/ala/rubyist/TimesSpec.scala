package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

class TimesSpec extends FunSpec with Matchers {
  describe("times") {
    import Times._

    it("should repeat given times") {
      var count = 0
      def push  = count += 1

      assert(count === 0)
      5 times push
      assert(count === 5)
    }

    it("should ignore 0 times") {
      0 times { fail("0 times{foo} should not be called") }
    }

    it("should ignore -1 times") {
      -1 times { fail("-1 times{foo} should not be called") }
    }
  }
}
