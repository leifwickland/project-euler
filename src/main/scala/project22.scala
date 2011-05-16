import collection.SortedSet
object project22 extends util.Project {
  def description = "What is the total of all the name scores in the file of first names?"

  def solve(args: Array[String]) = {
    val names = SortedSet[String]() ++ io.Source.fromFile("input/project22.names.txt").getLines.next.split(',').map(_.trim.replace("\"", ""))
    println(names.zipWithIndex.map{case (name, index) => nameValue(name) * (index + 1)}.sum)
  }

  def nameValue(s: String): Int = s.foldLeft(0){_ + charValue(_)}
  def charValue(c: Char): Int = c.toUpper - 'A' + 1

  override def test() {
    verify(53, nameValue("COLIN"))
  }
}
