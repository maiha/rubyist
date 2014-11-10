package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class DigestTest extends FunSpec with ShouldMatchers {
  describe("Digest.MD5") {
    describe("should generate hexdigest") {
      val md5sum = Digest.MD5.hexdigest("ruby")
      md5sum should equal("58e53d1324eef6265fdb97b08ed9aadf")
    }
  }
}
