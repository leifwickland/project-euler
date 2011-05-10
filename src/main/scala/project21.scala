object project21 extends util.Project {
  def description = "Evaluate the sum of all the amicable numbers under 10000."

  def solve(args: Array[String]) {
    val amicables = getAmicablePairs(10000)
    println("Amicable pairs:")
    amicables.foreach(println(_))
    println()
    println("Sum of amicable numbers: " + amicables.map{case(x,y) => x+y}.sum)
  }
  
  def getAmicablePairs(limit: Int) = {
    1 until limit map { (i) => (i, getProperDivisorSum(i)) } filter { case (i, sum) => i < sum && sum < limit && getProperDivisorSum(sum) == i }
  }
  def getProperDivisorSum(n: Int) = getProperDivisors(n).sum
  def getProperDivisors(n: Int) = for (i <- 1.to(n / 2) if 0 == (n % i)) yield i

  override def test {
    verify(Seq(1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110), getProperDivisors(220))
    verify(Seq(1, 2, 4, 71, 142), getProperDivisors(284))
    verify(284, getProperDivisorSum(220))
    verify(220, getProperDivisorSum(284))
  }
}
