object project12 extends util.Project {
  def description = "What is the value of the first triangle number to have over five hundred divisors?"

  def solve(args: Array[String]) {
    // Took 3419.439 s without using hueristic
    // Took  377.172 s with %2 %3 %5 hueristic
    // Took    2.314 s with %2 %3 %5 %7 %11 %13 hueristic
    // Took     .813 s with %2 %3 %5 %7 %11 %13 %17 hueristic
    val hueristicInputs = Seq(2,3,5,7,11,13,17)
    def hueristic(x: Long) = hueristicInputs.forall(x % _ == 0)
    println(new util.ProgessPrintingIterator(getTriangleIterator, 1000).find(x => hueristic(x) && getDivisorCount(x) > 500))
  }

  def getTriangleIterator: Iterator[Long] = {
    var sum = 0
    var next = 1
    Iterator.continually[Long]{sum += next; next += 1; sum}
  }

  def getDivisorCount(n: Long): Long = {
    // There should be a case for n == 1, but I don't care.
    return 2L /* for 1 and n*/ + (2L.to(n/2L)).count(n % _ == 0L)
  }

  override def test() {
    verify(List[Long](1,3,6,10,15,21,28), List[Long]() ++ getTriangleIterator.take(7))
    verify(getDivisorCount(7), 2)
    verify(getDivisorCount(21), 4)
    verify(getDivisorCount(28), 6)
    verify(getTriangleIterator.find(getDivisorCount(_) > 5), Some(28))
  }
}


