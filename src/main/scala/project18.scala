object project18 extends util.Project {
  def description = "Find the maximum sum travelling from the top of the triangle to the base."

  def solve(args: Array[String]) = println("Max Path: " + getMaxPath(readTriangle(realTriangle)))

  implicit object PathOrd extends Ordering[Path] {
    def compare(a: Path, other: Path): Int = a.weight.compare(other.weight)
  }

  def getMaxPath(triangle: Triangle) = {
    val lastLevel = triangle.length - 1
    val penultimateLevel = lastLevel - 1
    val maxRemaining = getMaxRemaining(triangle)
    var max = 0
    var maxPath: Path = null
    var q = scala.collection.mutable.PriorityQueue[Path](new Path(triangle, List(0), triangle.head.head, 0))
    while (!q.isEmpty) {
      val path = q.dequeue
      println("Current: " + path)
      if (path.getLevel < penultimateLevel) {
        q ++= path.nextTwo.filter(max < _.upperBound(maxRemaining))
      }
      else if (path.getLevel == penultimateLevel) {
        for (child <- path.nextTwo if (child.getSum > max)) {
          val oldMax = max
          max = child.getSum
          maxPath = child
          println("Max is now: " + max + " up from " + oldMax)
          println("Beginning cull. q.length = " + q.length)
          q = q.filter(max < _.upperBound(maxRemaining))
          println("Finished cull. q.length = " + q.length)
        }
      }
      else {
        throw new Exception("What is that doing in the queue?\n  " + path)
      }
    }
    maxPath
  }

  override def test() {
    val t = readTriangle(testTriangle)
    verify(t.head.head, 3)
    verify(t.last.last, 3)
    verify(t.last.head, 8)
    def verifyTriangle(triangle: Triangle) = triangle.zipWithIndex.foreach(x => verify(x._1.length, x._2 + 1))
    verifyTriangle(t)
    val rt = readTriangle(realTriangle)
    verifyTriangle(rt)
    val testMaxPath = getMaxPath(t)
    verify(testMaxPath.getSum, 23)
  }

  type Triangle = Array[Array[Int]]

  def getMaxRemaining(triangle: Triangle): Vector[Int] = {
    val maxes = triangle.map(_.max)
    for (i <- (maxes.length - 1).until(0,-1)) {
      maxes(i-1) += maxes(i)
    }
    Vector[Int]() ++ maxes
  }

  def readTriangle(input: String): Triangle = readTriangle(input.lines)

  def readTriangle(reader: Iterator[String]): Triangle = {
    reader.map(_.split(' ').map(_.trim).filter(_.length>0).map(_.toInt)).toArray
  }


  class Path(triangle: Triangle, indexes: List[Int], sum: Int, level: Int) {
    val weight: Int = sum + level
    def +(index: Int): Path = {
      new Path(this.triangle, this.indexes.+:(index), this.sum + triangle(this.level + 1)(index), level + 1)
    }
    def nextTwo: List[Path] = {
      List(this + this.indexes.head, this + (this.indexes.head + 1))
    }
    def upperBound(maxRemaining: Vector[Int]): Int = this.sum + maxRemaining(this.level + 1)
    override def toString = this.sum + "=" + this.indexes.reverse.zipWithIndex.map(i => triangle(i._2)(i._1)).toString + " @" + this.level
    def getLevel = this.level
    def getSum = this.sum
  }

  val testTriangle =
""" 3
    7 4
    2 4 6
    8 5 9 3 """

  val realTriangle = 
""" 75
    95 64
    17 47 82
    18 35 87 10
    20 04 82 47 65
    19 01 23 75 03 34
    88 02 77 73 07 63 67
    99 65 04 28 06 16 70 92
    41 41 26 56 83 40 80 70 33
    41 48 72 33 47 32 37 16 94 29
    53 71 44 65 25 43 91 52 97 51 14
    70 11 33 28 77 73 17 78 39 68 17 57
    91 71 52 38 17 14 91 43 58 50 27 29 48
    63 66 04 68 89 53 67 30 73 16 69 87 40 31
    04 62 98 27 23 09 70 98 73 93 38 53 60 04 23 """
}
