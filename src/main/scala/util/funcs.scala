package util

object funcs {
  def factorial(n: Int) = {
     @scala.annotation.tailrec
     def loop(n: Int, acc: Int): Int = if (n <= 0) acc else loop(n - 1, acc * n)
     loop(n, 1)
  }

  def factorial(n: Long) = {
    @scala.annotation.tailrec
     def loop(n: Long, acc: Long): Long = if (n <= 0) acc else loop(n - 1, acc * n)
     loop(n, 1)
  }

  def factorial(n: BigInt) = {
    @scala.annotation.tailrec
     def loop(n: BigInt, acc: BigInt): BigInt = if (n <= 0) acc else loop(n - 1, acc * n)
     loop(n, 1)
  }

  def firstNLetters(n: Int, from: Char = 'A'): String = (for (c <- 'A'.until(('A' + n).toChar)) yield c).mkString
}
