object project336 extends Project {
  def description = "Maximix Arrangements: Find the 2011th lexicographic maximix arrangement for eleven carriages."

  def run(args: Array[String]) {
    println(getMaximix(4))
    test
    println(getMaximix(11)(2010))
  }

  def test() {
    println("Starting tests")
    assert(("a", 0) == solve("a"))
    assert(("ab", 0) == solve("ab"))
    assert(("ab", 1) == solve("ba"))
    assert(("abcd", 0) == solve("abcd"))
    assert(("abcd", 1) == solve("adcb"))
    assert(("abcd", 5) == solve("dacb"))  
    assert(("abcd", 5) == solve("dbac"))
    assert("a".permutations.toList.length == 1)
    assert("ab".permutations.toList.length == 2)
    assert("abc".permutations.toList.length == 6)
    assert(getSolutionsSortedByLength("a").length == 1)
    assert(getSolutionsSortedByLength("abcd").length == 24)
    assert(List(("dacb",5), ("dbac",5)) == getMaximix(4))
    assert(24 == getMaximix(6).length)
    assert("dfaecb" == getMaximix(6)(9)._1)
    assert(firstNLetters(1) == "a")
    assert(firstNLetters(4) == "abcd")
    println("Tests passed")
  }

  def firstNLetters(n: Int): String = (for (c <- 'a'.until(('a' + n).toChar)) yield c).mkString
  
  def getMaximix2(length: Int) = { 
    val solutions = getSolutionsSortedByLength(firstNLetters(length))
    val max = solutions.head._2
    solutions.takeWhile{_._2 == max}.sortBy{_._1}
  }

  def getMaximix(length: Int) = { 
    val order = firstNLetters(length)
    getSolutionsOfLength(order, getLongestSolution(order)._2).toList.sortBy{_._1}
  }

  def getSolutionsOfLength(order: String, length: Int) = {
    order.permutations.map{(p) => (p, solve(p)._2)}.withFilter{_._2 == length}
  }

  var i: Int = 0
  def getLongestSolution(order: String) = {
    this.i = 0
    var longestSolution = ("", 0)
    for (p <- order.permutations) {
      val s = solve(p)
      if (this.i % 10000 == 0) {
        printf("On iteration %d=%s  (%3.3f) p=%s\n", this.i, p, this.i / 39916800.0 * 100.0, s.toString)
      }
      i += 1
      if (longestSolution._2 < s._2) {
        longestSolution = s
      }
    }
    longestSolution
  }

  def getSolutionsSortedByLength(order: String) = {
    this.i = 0
    order.permutations.map{ (p) => 
      if (this.i % 1000 == 0) {
        printf("On iteration %d=%s  (%3.3f)\n", i, p, i / 39916800.0 * 100.0)
      }
      i += 1
      (p, solve(p)._2)
      }.toList.sortBy{-_._2}
  }

  def solve(order: String, c: Char = 'a'): (String, Int) = {
    if (order.length <= 1) {
      return (order, 0)
    }
    val index = order.indexOf(c)
    index match {
      case -1 => throw new Exception("Danger! Could not find " + c + " in " + order)
      case 0 => {
        val sub = solve(order.tail, (c + 1).toChar)
        (c + sub._1, sub._2)
      }
      case _ if (order.length - 1 == index) => {
        val sub = solve(order.reverse, c)
        (sub._1, 1 + sub._2)
      }
      case _ =>  {
        val splits = order.splitAt(index)
        val sub = solve(splits._1 + splits._2.reverse, c)
        (sub._1, 1 + sub._2)
      }
    }
  }
}
