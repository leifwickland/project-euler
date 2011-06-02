import scala.collection._

object project79 extends util.Project {
  def description = "By analysing a user's login attempts, can you determine the secret numeric passcode?"

  def solve(args: Array[String]) = println(findLongestPath(attempts))

  override def test() {
    verify("12345", findLongestPath(testAttempts))
  }

  def findLongestPath(graph: Graph) = {
    var longest = ""
    val stack = mutable.ArrayStack[String]("S")
    while (stack.nonEmpty) {
      var current = stack.pop
      graph.get(current.last).foreach { 
        _.foreach { next =>
          next match {
            case 'E' => if (current.length >= longest.length) longest = current + next
            case _ => stack.push(current + next)
          }
        }
      }
    }
    longest.substring(1, longest.length - 1)
  }

  type Graph = mutable.MultiMap[Char,Char]

  def convertAttemptsToGraph(attempts: Array[Seq[Char]]): Graph = {
    val graph = new mutable.HashMap[Char,mutable.Set[Char]] with Graph
    attempts.foreach{_.sliding(2).foreach(pair => graph.addBinding(pair(0), pair(1)))}
    graph
  }

  def convertStringToAttempts(s: String) = new scala.util.matching.Regex("\\s+").split(s).map('S' +: _.toSeq :+ 'E')

  def testAttempts = convertAttemptsToGraph(convertStringToAttempts("""
    123
    345
    234
    124
    245"""))

  def attempts = convertAttemptsToGraph(convertStringToAttempts("""
  319
  680
  180
  690
  129
  620
  762
  689
  762
  318
  368
  710
  720
  710
  629
  168
  160
  689
  716
  731
  736
  729
  316
  729
  729
  710
  769
  290
  719
  680
  318
  389
  162
  289
  162
  718
  729
  319
  790
  680
  890
  362
  319
  760
  316
  729
  380
  319
  728
  716
  """))
}
