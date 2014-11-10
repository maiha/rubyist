package sc.ala.rubyist

/**
 * Intに対する拡張機能
 */
object Times {
  /**
   * n回繰り返す
   * (0もしくは負値の場合は一度も実行されない)
   *
   * @example {{{
   *   def foo: Unit = ...
   *   5 times { foo }
   * }}}
   */
  implicit class Times(val n: Int) extends AnyVal {
    def times[A](f: => A): Unit = { for(_ <- 1 to n) f }
  }
}
