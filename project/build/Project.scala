import sbt._

class YourProject(info: ProjectInfo) extends DefaultProject(info) with de.tuxed.codefellow.plugin.CodeFellowPlugin { 
}
