package project

object Main {

  def main(args: Array[String]) {
    println("Test")
    val line = convertToInt(readLine())

  }

  def convertToInt(line: String): Int = try {
    line.toInt
  } catch {
    case e:NumberFormatException => 0
  }
}
