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

  def fibonacci() = {
    class Fibber {
      // If I were a better scalist I'd understand why fib has to be declared as a member inside of an 
      // object to work correclty and could probably figure out a way around it, but I'm not.
      val fib: Stream[BigInt] = Stream.cons(0, Stream.cons(1, fib.zip(fib.tail).map(p => p._1 + p._2)))
      def getFib = fib
    }
    (new Fibber).getFib
  }
}
