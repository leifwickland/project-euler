package util

object StringImplicits {
  implicit def x(s: String): Wrapper = new Wrapper(s)
  class Wrapper(s: String) {
    def isPalindrome: Boolean = s.startsWith(s.takeRight(s.length / 2).reverse)
  }
}

