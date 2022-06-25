package lambdas

fun main() {
  //sampleStart

  val dupLambda1: (String) -> String = { s: String -> s + s }

  val dupLambda2: (String) -> String = { s -> s + s }

  val dupLambda3: (String) -> String = { it -> it + it }

  val dupLambda4: (String) -> String = { it + it }

  val dupLambda5 = { s: String -> s + s }
  //sampleEnd
}