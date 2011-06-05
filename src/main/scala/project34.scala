import util.funcs._

object project34 extends util.Project {
  def description = "Find the sum of all numbers which are equal to the sum of the factorial of their digits."

  def solve(args: Array[String]) = {
    var matches = List[Int]()
    for (i <- 10 to (Int.MaxValue-2) if sumOfFactorialOfDigits(i) == i) {
      matches = matches :+ i
      println("Found match %d.  (%s) Sum=%d".format(i, matches.mkString(","), matches.sum))
    }
  }

  def sumOfFactorialOfDigits(i: Int): Int = i.toString.map(x=>factorial(x.asDigit)).sum

  override def test() {
    verify(6, factorial(3))
    verify(145, sumOfFactorialOfDigits(145))
  }
}


