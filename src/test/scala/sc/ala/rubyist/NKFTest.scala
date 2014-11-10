package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

class NKFTest extends FunSpec with Matchers {
  def utf8  = Pathname("src/test/resources/Pathname/hello.txt.utf8")
  def sjis  = Pathname("src/test/resources/Pathname/hello.txt.sjis")

  describe("NKF") {
    // ----------------------------------------------------------------------
    // Accessors
    it("should convert from sjis to utf8") {
      val hello = Pathname(sjis.path, "Shift_JIS").read
      NKF.nkf("-w", hello) should be("こんにちわ\n")
    }
  }
}
