package util

// Stolen from http://michid.wordpress.com/2009/02/23/function_mem/ and then cleaned up a bit
class Memoize1[-T, +R](f: T => R) extends (T => R) {
  private[this] val vals = scala.collection.mutable.Map.empty[T, R]

  def apply(x: T): R = {
    vals.get(x) match {
      case Some(value) => value
      case None => 
        val y = f(x)
        vals(x) = y
        y
    }
  }
}

object Memoize1 {
  def apply[T, R](f: T => R) = new Memoize1(f)
}
