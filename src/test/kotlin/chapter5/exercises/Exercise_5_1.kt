package chapter5.exercises

import chapter3.List
import chapter3.Nil
import chapter3.exercises.reverse
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import chapter3.Cons as ConsList

/**
 * Re-enable the tests by removing the `!` prefix!
 */
class Exercise_5_1 : WordSpec({
    //tag::init[]
    fun <A> Stream<A>.toList(): List<A> {
        tailrec fun iterate(xs: Stream<A>, acc: List<A>): List<A> = when (xs) {
            is Empty -> acc
            is Cons -> iterate(xs.tail(), ConsList(xs.head(), acc))
        }
        return reverse(iterate(this, Nil))
    }
    //end::init[]

    "Stream.toList" should {
        "force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
