package chapter5.exercises

import chapter3.List
import chapter5.Stream
import chapter5.Stream.Companion.cons
import chapter5.solutions.toList
import chapter5.solutions.take
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

/**
 * Re-enable the tests by removing the `!` prefix!
 */
class Exercise_5_10 : WordSpec({

    //tag::init[]
    fun fibs(): Stream<Int> {
        fun iterate(current: Int, next: Int): Stream<Int> {
            return cons({ current }, { iterate(next, (current + next)) })
        }
        return iterate(0, 1)
    }
    //end::init[]

    "fibs" should {
        "return a Stream of fibonacci sequence numbers" {
            fibs().take(10).toList() shouldBe
                List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        }
    }
})
