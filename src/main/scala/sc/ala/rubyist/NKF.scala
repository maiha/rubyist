package sc.ala.rubyist

import scala.sys.process._

object NKF {
  class CannotConvert extends RuntimeException

  def nkf(opt:String, text:String) = (new NKF(opt, text))()
  def cmd = "nkf"
}

class NKF(opt:String, text:String) {
  def apply(): String = {
    Tempfile("nkf-src") {src:Pathname => src.write(text)
      val cmd = "%s %s %s".format(NKF.cmd, opt, src.path)
      return Process(cmd) !!
    }
    throw new NKF.CannotConvert
  }
}