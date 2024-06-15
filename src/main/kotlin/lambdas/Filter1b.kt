package lambdas

fun main() {
  //sampleStart
  fun filter(
    list: List<Int>,
    predicate: (Int) -> Boolean,
  ): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
      if (predicate(element)) result.add(element)
    }
    return result
  }

  val nums = listOf(0, 1, 2, 3, 4, 5, 6)
  val evens = filter(nums) { it % 2 == 0 }
  val by3s = filter(evens) { it % 3 == 0 }

  println(evens)
  println(by3s)
  //sampleEnd
}
