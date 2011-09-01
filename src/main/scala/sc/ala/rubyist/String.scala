package sc.ala.rubyist

object String {

  class RubyString(str: String) { 
//    def scan(pattern: String) =
    def chomp = str.replaceFirst("\n$", "")
  }

  implicit def stringToRubyString(str: String) = new RubyString(str)
}
