object project24 extends util.Project {
  def description = "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?"

  def solve(args: Array[String]) = println(nthPermutationOfString(0.to(9), 1e6.toInt))

  def nthPermutationOf(x: Range, n: Int) = x.permutations.drop(n-1).next.mkString

  // This version seems to run consistenly faster, interestingly
  def nthPermutationOfString(x: Range, n: Int) = x.mkString.permutations.drop(n-1).next

  override def test() {
    verify(nthPermutationOfString(0.to(2), 6), "210")
  }
}


