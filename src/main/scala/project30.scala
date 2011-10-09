object project30 extends util.Project {
  def description = "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits."

  def solve(args: Array[String]) {
    println(matches(10000000, 5).sum)
  }

  def matches(max: Int, power: Int) = 2.until(max).par.filter(i => i == nthPowerSum(i, power))

  def nthPowerSum(x: Int, power: Int): Int = x.toString.map(i => math.pow(i.asDigit, power).toInt).sum

  override def test() {
    verify(matches(10000, 4).sum == 19316)
  }
}
