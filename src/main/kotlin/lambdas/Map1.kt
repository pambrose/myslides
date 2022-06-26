package lambdas

fun main() {
  //sampleStart

  fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = ArrayList<R>(10)
    for (item in this) {
      result.add(transform(item))
    }
    return result
  }

  val intList = listOf(0, 1, 2, 3, 4, 5, 6).map { it * 2 }

  println(intList)
  //sampleEnd
}
