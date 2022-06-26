package lambdas

fun main() {
  //sampleStart

  fun greeting(): String {
    return "Hello"
  }

  val x: () -> String = ::greeting
 //sampleEnd
}