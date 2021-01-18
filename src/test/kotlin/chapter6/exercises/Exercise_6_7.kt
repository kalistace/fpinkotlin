package chapter6.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.solutions.foldRight
import chapter6.RNG
import chapter6.Rand
import chapter6.rng1
import chapter6.solutions.map2
import chapter6.unit
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

/**
 * TODO: Re-enable tests by removing `!` prefix!
 */
class Exercise_6_7 : WordSpec({

    //tag::init[]
    fun <A> sequence(fs: List<Rand<A>>): Rand<List<A>> = { rng ->
        when (fs) {
            Nil -> unit(List.empty<A>())(rng)
            is Cons -> {
                val (currentRand, nextRng) = fs.head(rng)
                val (tailRand, endRng) = sequence(fs.tail)(nextRng)
                Pair(Cons(currentRand, tailRand), endRng)
            }
        }
    }
    //end::init[]

    //tag::init2[]
    fun <A> sequence2(fs: List<Rand<A>>): Rand<List<A>> =
        foldRight(fs, unit(List.empty<A>())) { rand, acc ->
            map2(rand, acc) { head, tail -> Cons(head, tail) }
        }
    //end::init2[]

    fun ints(count: Int, rng: RNG): Pair<List<Int>, RNG> {
        fun go(c: Int): List<Rand<Int>> =
            if (c == 0) Nil
            else Cons({ r -> Pair(r.nextInt().first, r.nextInt().second) }, go(c - 1))
        return sequence(go(count))(rng)
    }


    "sequence" should {

        "combine the results of many actions using recursion" {

            val combined: Rand<List<Int>> =
                sequence(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }

        """!combine the results of many actions using
            foldRight and map2""" {

            val combined2: Rand<List<Int>> =
                sequence2(
                    List.of(
                        unit(1),
                        unit(2),
                        unit(3),
                        unit(4)
                    )
                )

            combined2(rng1).first shouldBe
                List.of(1, 2, 3, 4)
        }
    }

    "ints" should {
        "!generate a list of ints of a specified length" {
            ints(4, rng1).first shouldBe
                List.of(1, 1, 1, 1)
        }
    }
})
