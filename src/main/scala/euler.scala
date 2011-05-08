object euler {
  val projects = Set(project11, project16, project20, project13, project14, project25, project48)
  def main(args: Array[String]) {
    if (args.length == 0) {
      println("\nUsage: euler <projectN> [more args]\n")
      exit(0);
    }
    val projectName = args(0)
    projects.find { _.name == projectName } match {
      case None => {
        printf("\nSorry, I couldn't find a project named %s.\n", projectName)
        exit(1)
      }
      case Some(project) => {
        printf("Running %s:\n  %s\n", project.name, project.description)
        val startTime = System.currentTimeMillis
        project.run(args.tail)
        printf("\nRun Time: %d ms\n\n", System.currentTimeMillis - startTime)
      }
    }
  }
}
