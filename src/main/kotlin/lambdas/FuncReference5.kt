package lambdas

fun main() {
  //sampleStart

  fun greeting(name: String) {
    println("Hello, $name")
  }

  val x: (String) -> Unit = ::greeting
  //sampleEnd
}
