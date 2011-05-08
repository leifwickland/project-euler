object project20 extends Project {
  def description = "Find the sum of digits in 100!"

  def run(args: Array[String]) {
    val fact100 = factorial(100)
    println("100! = " + fact100)
    println("Sum of digits in 100!: " + fact100.toString.map{_-'0'}.sum)

  }

  def factorial(x: BigInt): BigInt = if (x == 1) 1 else x * factorial(x - 1)
}

