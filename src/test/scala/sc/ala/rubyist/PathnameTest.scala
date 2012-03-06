import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import sc.ala.rubyist.Pathname

class PathnameTest extends Spec with ShouldMatchers with FUtils {
  def langs = Pathname("src/test/resources/Pathname/langs.txt")
  def utf8  = Pathname("src/test/resources/Pathname/hello.txt.utf8")
  def sjis  = Pathname("src/test/resources/Pathname/hello.txt.sjis")

  import java.nio.charset.MalformedInputException
  import java.nio.charset.UnmappableCharacterException

  describe("Pathname") {
    // ----------------------------------------------------------------------
    // Accessors
    describe("should act as Path") {
      langs.name should equal("langs.txt")
      langs.path should equal("src/test/resources/Pathname/langs.txt")
    }

    describe("should provide extname") {
      Pathname("foo.log").extname should equal(".log")
      Pathname("foo").extname should equal("")
    }

    describe("should provide basename") {
      Pathname("/tmp/foo.log").basename.path should equal("foo.log")
      Pathname("/tmp/foo").basename.path should equal("foo")
      Pathname("foo.log").basename.path should equal("foo.log")
      Pathname("foo").basename.path should equal("foo")
    }

    // ----------------------------------------------------------------------
    // Actions
    describe("should write and read files") {
      tmpDir("tmp_path") {
	val path = Pathname("tmp_path/foo.txt")
	path.write("rubyist")
	path.read should equal("rubyist")
      }
    }

    describe("should append data") {
      tmpDir("tmp_path") {
	val path = Pathname("tmp_path/foo.txt")
	// write to empty file
	path.append("rubyist")
	path.read should equal("rubyist")

	// append to the file
	path.append("!rubyist")
	path.read should equal("rubyist!rubyist")
      }
    }

    describe("should automatically create parent directory in write") {
      tmpDir("tmp_path") {
	val path = Pathname("tmp_path/Pathname/write/should/create/parent")
	path.parent.exists should equal(false)
	path.write("xxx")
	path.parent.exists should equal(true)
      }
    }
    
    describe("should read lines") {
      langs.readlines should equal(List("Ruby","Scala"))
    }

    describe("should invoke methods of Path") {
      tmpDir("tmp_path") {
	Pathname("tmp_path").exists should be(true)
	Pathname("tmp_path/foo").exists should be(false)
      }
    }

    describe("should make path") {
      tmpDir("tmp_path") {
	val path = Pathname("tmp_path/foo/bar")
	path.exists should be(false)
	path.isDirectory should be(false)

	path.mkpath
	path.exists should be(true)
	path.isDirectory should be(true)
      }
    }

    describe("should touch an empty file") {
      tmpDir("tmp_path") {
        val path = Pathname("tmp_path/foo/bar")
        path.exists should be(false)
        path.touch
        path.exists should be(true)
        path.read should be("")
      }
    }

    describe("should unlink path") {
      tmpDir("tmp_path") {
        val path = Pathname("tmp_path/foo/bar")
        path.exists should be(false)
        path.touch
        path.exists should be(true)
        path.unlink
        path.exists should be(false)
      }
    }

    describe("should concat pathname") {
      val path = Pathname("a") + Pathname("b")

      path.getClass should equal(Pathname("").getClass)
      path.path should equal("a/b")
    }

    describe("should concat path string") {
      val path = Pathname("a") + "b"

      path.getClass should equal(Pathname("").getClass)
      path.path should equal("a/b")
    }

/* // This doesn't pass. why???
    describe("should convert implicitly String to Pathname") {
      val path = "a" + Pathname("b")

      path.getClass should equal(Pathname("").getClass)
      path.path should equal("a/b")
    }
    */
  }

  // ----------------------------------------------------------------------
  // Multibyte strings
  describe("Pathname#read") {
    describe("can read ascii file") {
      try { langs.read } catch {
	case e:MalformedInputException =>
	  fail("langs.read raised MalformedInputException")
      }
    }

    describe("can read UTF-8 file") {
      try { utf8.read } catch {
	case e:MalformedInputException =>
	  fail("langs.read raised MalformedInputException")
      }
    }

    describe("should fail to read Shift_JIS file") {
      evaluating {
	sjis.read
      } should produce [MalformedInputException]
    }

    describe("can read Shift_JIS file with Shift_JIS charset") {
      try {
	Pathname(sjis.path, "Shift_JIS").read
      } catch {
	case e:MalformedInputException =>
	  fail("langs.read raised MalformedInputException")
      }
    }

    describe("should fail to read UTF-8 file with Shift_JIS charset") {
      evaluating {
	Pathname(utf8.path, "Shift_JIS").read
      } should produce [UnmappableCharacterException]
    }
  }
}
