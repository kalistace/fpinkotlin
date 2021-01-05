package chapter5.exercises

import chapter3.List
import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter5.Stream
import chapter5.solutions.take
import chapter5.solutions.toList
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init[]
fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> {
    return when (val maybePair = f(z)) {
        is Some ->
            Stream.cons({ maybePair.get.first }, { unfold(maybePair.get.second, f) })
        is None -> Stream.empty()
    }
}
//end::init[]

/**
 * Re-enable the tests by removing the `!` prefix!
 */
class Exercise_5_11 : WordSpec({
    "unfold" should {
        """return a stream based on an initial state and a function
            applied to each subsequent element""" {
                unfold(0, { s: Int ->
                    Some(Pair(s, s + 1))
                }).take(5).toList() shouldBe
                    List.of(0, 1, 2, 3, 4)
            }
    }
})
