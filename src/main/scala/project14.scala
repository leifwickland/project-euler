object project14 extends util.Project {
  def description = "Find the longest sequence using a starting number under one million."
  def run(args: Array[String]) = args.headOption match {
    case Some("caching") => caching.run(args)
    case _ => naive.run(args)
  }

  object caching {
    val cache = new scala.collection.mutable.OpenHashMap[Long,Long](1.5e6.toInt)
    cache(1L)=1L

    def run(args: Array[String]) {
      2L.until(1e6.toLong).foreach(collatz)
      var max = (1L,1L)
      cache.withFilter{_._1 < 1e6.toLong }.foreach{(t) => if (t._2 > max._2) max = t}
      println("Max: " + max)
    }

    def collatz(n: Long): Long = {
      cache.get(n) match {
        case Some(c) => c
        case None => val c = 1L + (if (n % 2 == 0) collatz(n/2) else collatz(3 * n + 1)); cache(n) = c; c
      }
    }
  }

  object naive {
    def run(args: Array[String]) {
      var max = (1L,1L)
      for (i <- 1 until 1e6.toInt) {
        val c = collatz(i)
        if (c > max._2) {
          max = (i,c)
        }
      }
      println("Max: " + max)
    }

    def collatz(n: Long): Long = {
      if (n <= 1) {
        1
      }
      else if (n % 2 == 0) {
        collatz(n / 2) + 1
      }
      else {
        collatz(n * 3 + 1) + 1
      }
    }
  }
}
