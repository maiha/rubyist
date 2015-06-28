
package sc.ala.rubyist

import scala.tools.nsc.io.Path
import scala.io.Source
import java.io._
import java.nio.charset.Charset
import org.apache.commons.io.IOUtils
import sc.ala.rubyist.Digest._
import sc.ala.rubyist.Using._

object Pathname {
  implicit def pathnameToPath(x: Pathname): Path = x.path
  implicit def pathToPathname(x: Path): Pathname = new Pathname(x.path)

  def apply(path:String, charsetName: String = "UTF-8") =
    new Pathname(path, charsetName)
}

class Pathname(file: String, charsetName: String = "UTF-8") {
  lazy val logical  = Path(file)		   // logical path
  lazy val physical = Path(file)
  lazy val path     = physical.path

  def readlines(): List[String] = using(Source.fromFile(path, charsetName)) { _.getLines().toList }

  def read(): String = readlines.mkString

  def eachline(code: String => Unit) {
    var done = false
    var line:String = null
    val stream = new FileInputStream(path)
    val reader = new BufferedReader(new InputStreamReader(stream))

    try {
      while (!done) {
	line = reader.readLine()

	if (line == null)
	  done = true
	else
	  code(line)
      }
    } finally { reader.close }
  }

  def touch() = write("")

  def unlink() = (new File(path)).delete

  def write(raw: Array[Byte]): Unit = {
    mkparent
    using(new FileOutputStream(path)) { _.write(raw) }
  }

  def write(buffer: String): Unit = {
    mkparent
    using(new FileWriter(path)) { _.write(buffer) }
  }

  def append(buffer:String): Unit = {
    mkparent
    using(new FileWriter(path, true)) { _.write(buffer) }
  }

  def +(that:String): Pathname = Pathname(logical.resolve(that).path)
  def +(that:Pathname): Pathname = this + that.path
//  implicit def stringToPathname(str: String) = new Pathname(str)

  // Ruby#extname contains "." but Java#extension doesn't
  def extname  = if (logical.name.contains(".")) "."+logical.extension else ""
  def basename = Pathname(physical.name)

  def mkpath   = physical.createDirectory()
  def mkparent = physical.parent.createDirectory()
}

object Hashname {
  def apply(path:String) = new Hashname(path)
}

class Hashname(file:String) extends Pathname(file) {
  // [given] file is 'data/uesrs/910.xml'
  // [when]  digest of '910' is 'e205ee2a5de471a70c1fd1b46033a75f'
  // [then]  path is 'data/users/e/20/5ee/e205ee2a5de471a70c1fd1b46033a75f/910.xml'
  val digest = Digest.MD5.hexdigest(logical.stripExtension)
  val hashed = "%s/%s%s/%s%s%s/%s".format(digest(0), digest(1), digest(2), digest(3), digest(4), digest(5), digest)
  override lazy val physical = Path(logical.parent.resolve(hashed).resolve(logical.name).path)
}

