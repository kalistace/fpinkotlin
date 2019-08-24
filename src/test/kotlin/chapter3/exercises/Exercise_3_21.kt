package chapter3.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun add(xa: List<Int>, xb: List<Int>): List<Int> = TODO()
// end::init[]

class Exercise_3_21 : WordSpec({
    "list add" should {
        "!add elements of two corresponding lists" {
            chapter3.solutions.add(List.of(1, 2, 3), List.of(4, 5, 6)) shouldBe List.of(5, 7, 9)
        }
    }
})