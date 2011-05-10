object project16 extends util.Project {
  def description = "What is the sum of the digits of the number 2^1000?"
  def solve(args: Array[String]) {
    println(BigInt(2).pow(1000).toString.map({_ - '0'}).sum)
  }
}
