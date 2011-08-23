import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

import java.io.File
import org.apache.commons.io._

import sc.ala.rubyist._

class HathnameTest extends Spec with ShouldMatchers {
  def cleanDir(path:String): Unit = {
    FileUtils.deleteDirectory(new File(path))
    val dir = new File(path)
    dir.mkdirs
  }

  describe("Hashname") {
    describe("should hash directory") {
      // [given] file is 'data/uesrs/910.xml'
      val file = "data/users/910.xml"

      // [when] digest of '910' is 'e205ee2a5de471a70c1fd1b46033a75f'
      val hash = Hashname(file)

      // [then] path is "data/users/e/20/5ee/e205ee2a5de471a70c1fd1b46033a75f/910.xml"
      hash.path should equal("data/users/e/20/5ee/e205ee2a5de471a70c1fd1b46033a75f/910.xml")
    }

    describe("should write and read physical files") {
      cleanDir("tmp")
      val file = "tmp/users/910.xml"
      val path = Pathname(file)
      val hash = Hashname(file)

      path.exists should be(false)
      hash.exists should be(false)

      hash.write("rubyist")

      path.exists should be(false)
      hash.exists should be(true)

      hash.read should equal("rubyist")
    }
  }
}
