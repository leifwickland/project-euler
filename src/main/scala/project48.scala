object project48 extends Project {
  def description = "Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000."

  def run(args: Array[String]) {
    val sum = 1.to(1000).map{(x) => BigInt(x).pow(x)}.sum
    println("Sum: " + sum)
    println("Last 10 digits of sum: " + sum.toString.takeRight(10))
  }
}
