import util.funcs._

object project25 extends util.Project {
  def description = "What is the first term in the Fibonacci sequence to contain 1000 digits?"

  def solve(args: Array[String]) {
    var t = -1
    fibonacci.find{(x) => t += 1; x.toString.length >= 1000} match {
      case Some(x) => println("SUCCESS: " + t + " -> " + x)
      case _ => println("Eh.  That shouldn't have happened")
    }
  } 

  override def test {
    verify(fibonacci.apply(0), 0)
    verify(fibonacci.apply(1), 1)
    verify(fibonacci.apply(2), 1)
    verify(fibonacci.apply(3), 2)
    verify(fibonacci.apply(12), 144)
  }
}
