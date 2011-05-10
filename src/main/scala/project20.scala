object project20 extends util.Project {
  def description = "Find the sum of digits in 100!"

  def run(args: Array[String]) {
    val fact100 = util.funcs.factorial(BigInt(100))
    println("100! = " + fact100)
    println("Sum of digits in 100!: " + fact100.toString.map{_-'0'}.sum)
  }
}

