package sc.ala.rubyist

import sc.ala.rubyist.Tempfile.CannotCreate

object Tempfile {
  import java.lang.management.ManagementFactory

  class CannotCreate extends RuntimeException

  def tmpdir = "tmp/sc.ala.rubyist.Tempfile"
  def mxbean = ManagementFactory.getRuntimeMXBean.getName // 3691@I980
  def getpid = mxbean.split("@")(0).toInt

  def create(root: Pathname): Pathname = synchronized {
    root.mkparent
    for (i <- 1 to 10000; path = Pathname("%s-%s".format(root.path,i)) if (! path.exists)) {
      path.write("")
      return path
    }
    throw new CannotCreate
  }

  def apply(basename: String) = new Tempfile(basename)
}

class Tempfile(basename: String, root: String = Tempfile.tmpdir) {
  lazy val base = "%s-%s-%s".format(basename, Tempfile.getpid, Thread.currentThread.getName)
  lazy val path = Tempfile.create(Pathname(root) + base)

  def unlink = path.unlink

  def apply(block: Pathname => Unit) = {
    block(path)
    unlink
  }

  def apply(block: => Unit) = {
    block
    unlink
  }
}