package util
class ProgessPrintingIterator[T](inner: Iterator[T], interval: Int, total: Int = 0) extends Iterator[T] {
  val startTime = System.currentTimeMillis
  var i = 0

  override def next = { 
    val nextValue = inner.next
    i += 1
    if (1 == i % interval || i == total) {
      printf("Progress: %d", i)
      if (total > 0) {
        val completed = i / total.toDouble
        val elapsed = elapsedTime
        val estTotal = elapsed / completed
        val remaining = estTotal - elapsed
        printf(" (%1.2f%%) ETA: %s of %s", completed * 100.0, toTime(remaining), toTime(estTotal))
      }
      println(" current=" + nextValue)
    }
    nextValue
  }

  override def hasNext = inner.hasNext

  def toTime(seconds: Double) = {
    val secondsInMinute = 60
    "%d:%04.1f".format((seconds / secondsInMinute).toInt, (seconds % secondsInMinute).toDouble)
  }

  def elapsedTime = (System.currentTimeMillis - this.startTime) / 1000.0
}
