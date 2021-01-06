package chapter5.exercises

import chapter3.List
import chapter5.Stream
import chapter5.solutions.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

/**
 * Re-enable the tests by removing the `!` prefix!
 */
class Exercise_5_15 : WordSpec({

    //tag::tails[]
    fun <A> Stream<A>.tails(): Stream<Stream<A>> = unfold(this){

    }
    //end::tails[]

    fun <A, B> List<A>.map(f: (A) -> B): List<B> = when (this) {
        is ConsL -> ConsL(f(this.head), this.tail.map(f))
        is NilL -> NilL
    }

    "Stream.tails" should {
        "!return the stream of suffixes of the input sequence" {
            Stream.of(1, 2, 3)
                .tails()
                .toList()
                .map { it.toList() } shouldBe
                List.of(
                    ConsL(1, ConsL(2, ConsL(3, NilL))),
                    ConsL(2, ConsL(3, NilL)),
                    ConsL(3, NilL),
                    NilL
                )
        }
    }
})
