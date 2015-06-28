package sc.ala.rubyist

import org.scalatest._
import org.scalatest.matchers._

import java.io.File

import sc.ala.rubyist.String._

class StringTest extends FunSpec with Matchers {
  describe("String#chomp") {
    describe("should trim last CRLF") {
      "abc\n".chomp() should equal("abc")
    }
    describe("should not trim middle CRLF") {
      "a\nbc\n".chomp() should equal("a\nbc")
    }
    describe("should not affect non CRLF string") {
      "abc".chomp() should equal("abc")
    }
  }
}
