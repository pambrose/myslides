package lambdas

fun main() {
  //sampleStart
  fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {
    val result = ArrayList<T>()
    for (element in this) {
      if (predicate(element)) result.add(element)
    }
    return result
  }

  val nums: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6)
  val evens: List<Int> = nums.filter { it % 2 == 0 }
  val by3s: List<Int> = evens.filter { it % 3 == 0 }

  println(evens)
  println(by3s)
  //sampleEnd
}
