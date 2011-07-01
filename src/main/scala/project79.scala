import scala.collection._

object project79 extends util.Project {
  def description = "By analysing a user's login attempts, can you determine the secret numeric passcode?"

  def solve(args: Array[String]) = println(attempts.findLongestPath)

  override def test() {
    verify("12345", testAttempts.findLongestPath)
  }

  final class Graph(attempts: String) {
    private val startNode = 'S'
    private val endNode = 'E'
    private implicit def cToS(c: Char): String = c.toString
    private val graph = new mutable.HashMap[Char,mutable.Set[Char]] with mutable.MultiMap[Char,Char]
    convertAttemptsToGraph(convertStringToAttempts(attempts))

    def findLongestPath: String = findLongestPathImpl(startNode).stripPrefix(startNode).stripSuffix(endNode)

    private def findLongestPathImpl(current: String): String = {
      getSuccessors(current).map{ s => 
        s.last match {
          case 'E' => s
          case _ => findLongestPathImpl(s)
        }
      }.maxBy(_.length)
    }

    private def getSuccessors(current: String) = graph.getOrElse(current.last, Set()).map(current+)

    private def convertAttemptsToGraph(attempts: Array[Seq[Char]]) {
      attempts.foreach{_.sliding(2).foreach(pair => graph.addBinding(pair(0), pair(1)))}
    }

    private def convertStringToAttempts(s: String) = "\\s+".r.split(s).map(startNode +: _.toSeq :+ endNode)
  }

  def testAttempts = new Graph("""
    123
    345
    234
    124
    245""")

  def attempts = new Graph("""
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
  """)
}
