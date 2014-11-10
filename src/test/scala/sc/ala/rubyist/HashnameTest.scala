package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class HathnameTest extends FunSpec with ShouldMatchers with FileUtils {
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
      tmpDir("tmp_hash") {
	val file = "tmp_hash/users/910.xml"
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
}
