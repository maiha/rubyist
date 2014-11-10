package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

class HostTest extends FunSpec with Matchers {
  describe("Host") {
    // ----------------------------------------------------------------------
    // Accessors

    // Since I don't know how to stub yet, use concrete object instead.
    val host = CurrentHost

    describe("should provide name") {
      host.name.getClass should equal("".getClass)
    }
  }
}
