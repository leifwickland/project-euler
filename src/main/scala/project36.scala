import util.StringImplicits._

object project36 extends util.Project {
  def description = "Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2."

  def solve(args: Array[String]) {
    println(0.until(1e6.toInt).filter(_.toString.isPalindrome).filter(_.toBinaryString.isPalindrome).sum)
  }

  override def test() {
    verify("aa".isPalindrome, true)
    verify("aba".isPalindrome, true)
    verify("baba".isPalindrome, false)
    verify("baaba".isPalindrome, false)
    verify("abcba".isPalindrome, true)
    verify("abba".isPalindrome, true)
    verify(3.toBinaryString, "11")
    verify(1023.toBinaryString, "1111111111")
    verify(585.toString.isPalindrome, true)
    verify(585.toBinaryString.isPalindrome, true)
    verify(584.toString.isPalindrome, false)
    verify(584.toBinaryString.isPalindrome, false)
    verify(585, List(585, 584).filter(_.toString.isPalindrome).filter(_.toBinaryString.isPalindrome).sum)
  }

