import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import sc.ala.rubyist.Pathname
import sc.ala.rubyist.NKF

class NKFTest extends Spec with ShouldMatchers with FUtils {
  def utf8  = Pathname("src/test/resources/Pathname/hello.txt.utf8")
  def sjis  = Pathname("src/test/resources/Pathname/hello.txt.sjis")

  describe("NKF") {
    // ----------------------------------------------------------------------
    // Accessors
    describe("should convert from sjis to utf8") {
      val hello = Pathname(sjis.path, "Shift_JIS").read
      NKF.nkf("-w", hello) should be("こんにちわ\n")
    }
  }
}
