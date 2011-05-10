package util

abstract class Project extends Tester {
  def run(args: Array[String]) = args.headOption match { 
    case None => testWrapper; println; solve(args)
    case Some("test") => testWrapper
    case Some("solve") => solve(args.tail)
  }

  def solve(args: Array[String])

  def name(): String = this.getClass.getName.replaceAll("[$]", "")

  def description(): String
}

trait Tester {
  def testWrapper() {
    this.passedTests = 0
    println("Starting tests")
    test
    printf("Passed %d tests\n", this.passedTests)
  }

  var passedTests = 0

  def verify(assertion: Boolean) {
    if (!assertion) 
      throw new java.lang.AssertionError("Test failed.")
  }

  def verify[T](a: T, b: T) {
    if (a == b)
      passedTests += 1
    else 
      throw new java.lang.AssertionError("Test failed:\n  %s\n  !=\n  %s".format(a, b))
  }

  def test() { }
}
