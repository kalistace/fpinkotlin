package chapter6.exercises

import chapter6.RNG
import chapter6.solutions.double
import chapter6.solutions.doubleR
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import org.eclipse.jgit.diff.ContentSource

/**
 * TODO: Re-enable tests by removing `!` prefix!
 */
class Exercise_6_3 : WordSpec({

    //tag::init[]
    fun intDouble(rng: RNG): Pair<Pair<Int, Double>, RNG> = rng.nextInt()
        .let { (randomInt, nextRng) ->
            double(nextRng).let { (randomDouble, nextRng2) ->
                Pair(Pair(randomInt, randomDouble), nextRng2)
            }
        }

    fun doubleInt(rng: RNG): Pair<Pair<Double, Int>, RNG> =
        intDouble(rng).let { (intDouble, nextRng) ->
            Pair(Pair(intDouble.second,
                intDouble.first), nextRng)
        }

    fun double3(rng: RNG): Pair<Triple<Double, Double, Double>, RNG>  {
        val (d1, rng2) = doubleR(rng)
        val (d2, rng3) = doubleR(rng2)
        val (d3, rng4) = doubleR(rng3)
        return Pair(Triple(d1, d2, d3), rng4)
    }
    //end::init[]

    "intDouble" should {

        val doubleBelowOne =
            Int.MAX_VALUE.toDouble() / (Int.MAX_VALUE.toDouble() + 1)

        val unusedRng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> = TODO()
        }

        val rng3 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Pair(Int.MAX_VALUE, unusedRng)
        }

        val rng2 = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Pair(Int.MAX_VALUE, rng3)
        }

        val rng = object : RNG {
            override fun nextInt(): Pair<Int, RNG> =
                Pair(Int.MAX_VALUE, rng2)
        }

        "generate a pair of int and double" {
            val (id, _) = intDouble(rng)
            val (i, d) = id
            i shouldBe Int.MAX_VALUE
            d shouldBe doubleBelowOne
        }

        "generate a pair of double and int" {
            val (di, _) = doubleInt(rng)
            val (d, i) = di
            d shouldBe doubleBelowOne
            i shouldBe Int.MAX_VALUE
        }

        "generate a triple of double, double, double" {
            val (ddd, _) = double3(rng)
            val (d1, d2, d3) = ddd
            d1 shouldBe doubleBelowOne
            d2 shouldBe doubleBelowOne
            d3 shouldBe doubleBelowOne
        }
    }
})
