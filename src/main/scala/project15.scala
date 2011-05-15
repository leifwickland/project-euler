import scala.collection.mutable.Stack

// Assuming it doesn't run out of memory, this will solve the problem in about 3 hours.  
// 16 and level 20 is about 3 orders of magnitude larger.
// This code was sufficient to convince me that the answer could be found in oeis.org; it's http://oeis.org/A000984.
// It turns out, there are way, way easier ways to solve it once you know that.
object project15 extends util.Project {
  def description = "How many routes are there through a 20x20 grid?"

  def solve(args: Array[String]) {
    for (gridSize <- 1 to 20) {
      val before = System.currentTimeMillis
      val routes = getRoutes(gridSize)
      printf("%d: %d (in %1.3f s)\n", gridSize, routes, 0.001 * (System.currentTimeMillis - before))
    }
  }

  def getRoutes(gridSize: Int): Long = {
    val levels = gridSize * 2 + 1
    val lastLevel = levels - 1
    val penultimateLevel = lastLevel - 1
    val midLevel = gridSize
    val maxIndices: Vector[Int] = Vector[Int]() ++ 0.to(midLevel) ++ (midLevel-1).to(0,-1)
    var routes = 0: Long

    case class Route(level: Int, index: Int) {
      def getChildren: List[Route] = if (this.level < midLevel) this.getChildrenTop else this.getChildrenBottom
      private def getChildrenTop: List[Route] = List[Route](this.birth(0), this.birth(1))
      private def getChildrenBottom: List[Route] = {
        this.index match {
          case 0 => List[Route](this.birth(0))
          case index: Int if index == maxIndices(this.level) => List[Route](this.birth(-1))
          case _ => List[Route](this.birth(-1), this.birth(0)) 
        }
      }
      private def birth(delta: Int) = new Route(this.level + 1, (this.index + delta))
    }

    val stack = Stack[Route](new Route(1, 0)) // I know the routes are symmetric, so only solve half the left half of the problem
    while (!stack.isEmpty) {
      val route = stack.pop
      if (route.level >= penultimateLevel)
        routes += 1 
      else 
        stack.pushAll(route.getChildren)
    }

    return 2 * routes // Due to the aforementioned cheating
  }

  override def test() {
    verify(getRoutes(1), 2)
    verify(getRoutes(2), 6)
    verify(getRoutes(3), 20)
    verify(getRoutes(4), 70)
  }
}

// 0:     0
// 1:    0 1
// 2:   0 1 2
// 3:  0 1 2 3
// 4:   0 1 2
// 5:    0 1
// 6:     0
