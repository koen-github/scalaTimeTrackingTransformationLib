import org.scatitrali.XMLAsProject

object ConvertGnotimeToTaskjuggler {
  def main(args: Array[String]): Unit = {
    println("=====================================================")
    println("===================ScaTiTralI========================")
    println("This will convert the following file into an taskjuggler project: example1.xml")
    val pathName: String = "src/main/resources/example1.xml"
    val convertedProject: XMLAsProject = XMLAsProject(pathName)
    println("=====================================================")
    println(convertedProject)

  }
}
