package chapter5.exercises

import chapter4.Option
import chapter5.Boilerplate.isEmpty
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

/**
 * Re-enable the tests by removing the `!` prefix!
 */
class Exercise_5_14 : WordSpec({

    //tag::startswith[]
    fun <A> Stream<A>.startsWith(that: Stream<A>): Boolean =
        this
            .zipAll(that)
            .takeWhile { (_, item2) -> !item2.isEmpty() }
            .forAll { (item1, item2) -> item1 == item2 }
    //end::startswith[]

    "Stream.startsWith" should {
        "detect if one stream is a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(1, 2)
            ) shouldBe true
        }
        "detect if one stream is not a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(2, 3)
            ) shouldBe false
        }
    }
})
