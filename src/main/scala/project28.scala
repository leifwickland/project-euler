object project28 extends util.Project {
  def description = "What is the sum of both diagonals in a 1001 by 1001 spiral?"

  def solve(args: Array[String]) {
    println(getSumOfSpiral(1001))
  }

  def getSumOfSpiral(size: Int) = {
    var corner = 1
    var sum = 1
    for (size <- 3.to(size,2);
         corners <- 1.to(4)) {
      corner += (size - 1)
      sum += corner
    }
    sum
  }

  override def test {
    verify(1, getSumOfSpiral(1))
    verify(25, getSumOfSpiral(3))
    verify(101, getSumOfSpiral(5))
  }
}
