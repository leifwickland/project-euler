import scala.collection.mutable.ListBuffer
import util._
import util.funcs._

object project336 extends Project {
  def description = "Maximix Arrangements: Find the 2011th lexicographic maximix arrangement for eleven carriages."

  def run(args: Array[String]) = args.headOption match { 
    case None => test; solve
    case Some("test") => test
    case Some("solve") => solve
  }

  def solve() = println(getMaximix(11)(2010)) 

  def getMaximix(length: Int) = {
    val longestSolutions = ListBuffer[(String, Int)]((null, 0))  // Turns out, List has O(n) appends; ListBuffer has O(1). This runs 25x faster with ListBuffer.
    for (p <- new ProgessPrintingIterator(firstNLetters(length).permutations, 10000, factorial(length))) {
      val max = longestSolutions.head._2
      getRotations(p)._2 match {
        case length if length > max => longestSolutions.clear; longestSolutions += ((p, length))
        case length if length == max => longestSolutions += ((p, length))
        case _ => // Size does matter.
      }
    }
    longestSolutions
  }

  def getRotations(arrangement: String, c: Char = 'A'): (String, Int) = {
    if (arrangement.length <= 1) return (arrangement, 0)

    arrangement.indexOf(c) match {
      case -1 => throw new Exception("Danger! Could not find " + c + " in " + arrangement)
      case 0 =>
        val sub = getRotations(arrangement.tail, (c + 1).toChar)
        (c + sub._1, sub._2)
      case index if (arrangement.length - 1 == index) =>
        val sub = getRotations(arrangement.reverse, c)
        (sub._1, 1 + sub._2)
      case index => 
        val splits = arrangement.splitAt(index)
        val sub = getRotations(splits._1 + splits._2.reverse, c)
        (sub._1, 1 + sub._2)
    }
  }

  def test() { 
    println("Starting tests")
    assert(("A", 0) == getRotations("A"))
    assert(("AB", 0) == getRotations("AB"))
    assert(("AB", 1) == getRotations("BA"))
    assert(("ABCD", 0) == getRotations("ABCD"))
    assert(("ABCD", 1) == getRotations("ADCB"))
    assert(("ABCD", 5) == getRotations("DACB"))  
    assert(("ABCD", 5) == getRotations("DBAC"))
    assert("A".permutations.toList.length == 1)
    assert("AB".permutations.toList.length == 2)
    assert("ABC".permutations.toList.length == 6)
    assert(List(("DACB",5), ("DBAC",5)) == getMaximix(4))
    assert(24 == getMaximix(6).length)
    assert("DFAECB" == getMaximix(6)(9)._1)
    assert(firstNLetters(1) == "A")
    assert(firstNLetters(4) == "ABCD")
    assert(factorial(1) == 1)
    assert(factorial(11) == 39916800)
    println("Tests passed")
  }
}
