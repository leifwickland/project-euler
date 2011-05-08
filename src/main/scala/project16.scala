object project16 extends Project {
  def description = "What is the sum of the digits of the number 2^1000?"
  def run(args: Array[String]) {
    println(BigInt(2).pow(1000).toString.map({_ - '0'}).sum)
  }
}
