package sc.ala.rubyist

object Tap {
  /**
   * yield funcion, and then return self
   *
   * @example {{{
   *   val app: Application = new Application().tap{ _.start() }
   * }}}
   */
  implicit class Tap[A](val obj: A) extends AnyVal {
    def tap[B](f: => Unit): A = { f; obj }
    def tap[B](f: (A) => Unit): A = { f(obj); obj }
  }
}
