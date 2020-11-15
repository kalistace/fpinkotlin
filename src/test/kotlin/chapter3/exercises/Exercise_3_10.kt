package chapter3.exercises

import chapter3.List
import chapter3.solutions.foldRight
import chapter4.Boilerplate.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun sumL(xs: List<Int>): Int = foldLeft(xs, 0, { a, b -> a + b })

fun productL(xs: List<Double>): Double =
    foldLeft(xs, 1.0, { a, b -> a * b })

fun <A> lengthL(xs: List<A>): Int = foldLeft(xs, 0, { a, _ -> a + 1 })
// end::init[]

class Exercise_3_10 : WordSpec({
    "list sumL" should {
        "add all integers" {
            sumL(List.of(1, 2, 3, 4, 5)) shouldBe 15
        }
    }

    "list productL" should {
        "multiply all doubles" {
            productL(List.of(1.0, 2.0, 3.0, 4.0, 5.0)) shouldBe
                120.0
        }
    }

    "list lengthL" should {
        "count the list elements" {
            lengthL(List.of(1, 2, 3, 4, 5)) shouldBe 5
        }
    }
})
