package sc.ala.rubyist

import java.io.File
import org.apache.commons.io._

trait FileUtils {
  def removeDir(path:String): Unit = {
    FileUtils.deleteDirectory(new File(path))
  }

  def cleanDir(path:String): Unit = {
    removeDir(path)
    val dir = new File(path)
    dir.mkdirs
  }

  def tmpDir(path:String)(block: => Unit) {
    cleanDir(path)
    block
    removeDir(path)
  }
}

