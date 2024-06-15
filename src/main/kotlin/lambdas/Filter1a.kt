package lambdas

fun main() {
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

  //sampleStart

  val nums = listOf(0, 1, 2, 3, 4, 5, 6)
  val evens = filter(nums, { it % 2 == 0 })
  val odds = filter(nums) { it % 2 == 1 }

  println(evens)
  println(odds)
  //sampleEnd
}
