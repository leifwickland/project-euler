object project52 extends util.Project {
  def description = "Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits."
  val startTime = System.currentTimeMillis

  def solve(args: Array[String]) = solveFast

  def solvePretty {
    1L.to(Int.MaxValue).find(pass) match { case Some(x: Long) => printSolved(x); case None => println("Bug!") }
  }

  def pass(x: Long) = {
    val twoX = (x + x).toString.sorted
    3.to(6).forall(i => (i * x).toString.sorted == twoX)
  }

  def solveFast {
    var x: Long = 1L
    while (x > 0) {
      if  (x % 100000L == 0L) {
        println(sinceStart + x)
      }
      val threeX = x + x + x
      val sixXs = (threeX + threeX).toString.sorted
      if (sixXs.length > x.toString.length) {
        println(sinceStart + "Skipping from " + x)
        x = math.pow(10, math.log10(x).ceil).toLong
      } else if (threeX.toString.sorted == sixXs) {
        val twoX = x + x
        val twoXs = twoX.toString.sorted
        val fourXs = (twoX + twoX).toString.sorted
        val fiveXs = (5 * x).toString.sorted
        if ((sixXs == twoXs) && (sixXs == fourXs) && (fiveXs == sixXs)) {
          printSolved(x)
          x = -1L
        }
      }
      x += 1L
    }
  }

  def printSolved(x: Long) {
    println("Solved!")
    1.to(6).foreach(i => println(i + "x == " + (i * x)))
  }

  def sinceStart: String = "%010.3f: ".format((System.currentTimeMillis - startTime) / 1000.0)

}
