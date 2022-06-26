package lambdas

fun main() {
  //sampleStart
  fun filter(list: List<Int>, condition: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
      if (condition(element)) result.add(element)
    }
    return result
  }

  val nums = listOf(0, 1, 2, 3, 4, 5, 6)

  println(filter(nums) { it % 2 == 0 })
  println(filter(nums) { it % 3 == 0 })
  //sampleEnd
}
