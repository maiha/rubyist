package sc.ala.rubyist

import org.scalatest._
import org.scalamock.scalatest._

import java.io.ByteArrayOutputStream

class UsingSpec extends FunSpec with MockFactory {
  import Using._

  trait Closable {
    def close()
  }

  def closable = {
    val m = mock[Closable]
    (m.close _).expects()
    m
  }

  describe("using") {
    it("provide loan pattern") {
      using(new ByteArrayOutputStream) { out =>
      }
    }

    it("should invoke close") {
      using(closable) { f =>
      }
    }
  }
}
