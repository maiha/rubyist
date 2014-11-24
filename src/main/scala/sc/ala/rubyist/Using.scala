package sc.ala.rubyist

import scala.language.reflectiveCalls

object Using {
  /**
   * provide loan pattern which ensures to call `close' after the block
   *
   * @example {{{
   *   using(new ByteArrayOutputStream) { out => ... }
   * }}}
   */
  def using[A <: {def close()}, B](a: A)(f: A => B): B = try {
    f(a)
  } finally {
    a.close()
  }
}
