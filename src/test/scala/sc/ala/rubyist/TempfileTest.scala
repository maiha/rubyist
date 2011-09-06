import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import java.io.File

import sc.ala.rubyist._

class TempfileTest extends Spec with ShouldMatchers {
  describe("Tempfile") {
    // ----------------------------------------------------------------------
    // Accessors

    describe("should provide path") {
      val tmp  = new Tempfile("test")
      val path = tmp.path
      tmp.path.getClass should equal(Pathname("/").getClass)
      path.unlink
    }

    describe("should create tmpfile") {
      val tmp  = new Tempfile("test")
      val path = tmp.path
      tmp.path.exists should be(true)
      path.unlink
    }

    describe("should delete tmpfile by unlink") {
      val tmp  = new Tempfile("test")
      val path = tmp.path
      tmp.unlink
      tmp.path.exists should be(false)
    }

    describe("should delete tmpfile after block") {
      val tmp  = new Tempfile("test")
      val path = tmp.path
      tmp {
        path.exists should be(true)
      }
      path.exists should be(false)
    }

    describe("should delete tmpfile after block even if errors raised") {
      val tmp  = new Tempfile("test")
      val path = tmp.path

      try {
        tmp{ p:Pathname =>
          path.exists should be(true)
          1 / 0 // force error
        }
      } catch {
        case _ => // ignore
      }
      path.exists should be(false)
    }

    describe("should provide path in block(string)") {
      val tmp  = new Tempfile("test")
      val path = tmp.path
      tmp { p:Pathname => p should be(path) }
    }

    describe("should not conflict on parallel executions") {
      val tmp1 = new Tempfile("test")
      val tmp2 = new Tempfile("test")
      tmp1 { p1:Pathname =>
        p1.write("tmp1")
        tmp2 { p2:Pathname => p2.write("tmp2") }
        p1.read should be("tmp1")
      }
    }
  }
}
