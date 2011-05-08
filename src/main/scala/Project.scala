abstract class Project {
  def run(args: Array[String])
  def name(): String = this.getClass.getName.replaceAll("[$]", "")
  def description(): String
}
