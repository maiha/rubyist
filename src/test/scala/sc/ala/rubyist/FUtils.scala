import java.io.File
import org.apache.commons.io._

trait FUtils {
  def cleanDir(path:String): Unit = {
    FileUtils.deleteDirectory(new File(path))
    val dir = new File(path)
    dir.mkdirs
  }

  def tmpDir(path:String)(block: => Unit) {
    cleanDir(path)
    block
    cleanDir(path)
  }
}

