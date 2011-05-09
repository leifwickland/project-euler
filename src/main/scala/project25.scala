object project25 extends Project {
  def description = "What is the first term in the Fibonacci sequence to contain 1000 digits?"

  def run(args: Array[String]) {
    var t = -1
    fib.find{(x) => t+=1; x.toString.length >= 1000} match {
      case Some(x) => println("SUCCESS: " + t + " -> " + x)
      case _ => println("Eh.  That shouldn't have happened")
    }
  } 
  val fib: Stream[BigInt] = Stream.cons(0, Stream.cons(1, fib.zip(fib.tail).map(p => p._1 + p._2)))
}
