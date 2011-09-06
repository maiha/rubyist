
package sc.ala.rubyist

import _root_.scala.tools.nsc.io.Path
import scala.io.Source
import java.io._
import java.nio.charset.Charset
import sc.ala.rubyist.Digest._

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

  def readlines = {
    val in = Source.fromFile(path, charsetName)
    try { in.getLines().toList }
    finally { in.close }
  }
  def read: String = readlines.mkString

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

  def write(buffer:String): Unit = {
    mkparent
    val out = new FileWriter(path)
    try{
      out.write(buffer)
    }
    finally{ out.close }
  }

  def append(buffer:String): Unit = {
    mkparent
    val out = new FileWriter(path, true)
    try{
      out.write(buffer)
    }
    finally{ out.close }
  }

  def +(that:String): Pathname = Pathname(logical.resolve(that).path)
  def +(that:Pathname): Pathname = this + that.path
//  implicit def stringToPathname(str: String) = new Pathname(str)

  // Ruby#extname contains "." but Java#extension doesn't
  def extname  = if (logical.name.contains(".")) "."+logical.extension else ""

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
  val hashed = "%s/%s%s/%s%s%s/%s".format(digest(0), digest(1), digest(2),
		      digest(3), digest(4), digest(5), digest)
  override lazy val physical = Path(logical.parent.resolve(hashed).resolve(logical.name).path)
}

