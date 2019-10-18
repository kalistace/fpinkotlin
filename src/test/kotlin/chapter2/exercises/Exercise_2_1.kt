package chapter2.exercises

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentMapOf

//0, 1, 1, 2, 3, 5, 8, 13
class Exercise_2_1 : WordSpec({
    // tag::init[]
    //think inverse fibonacci sequence : 13, 8, 5, 3, 2, 1, 1, 0
    fun fib(i: Int): Int {
        tailrec fun fibAcc(n: Int, next: Int, current: Int): Int =
                if (n == 0) current
                else fibAcc(n - 1, current + next, next)
        return fibAcc(i, 1, 0)
    }
    // end::init[]

    //0, 1, 1, 2, 3, 5, 8, 13
    fun fibNonTailRec(i: Int): Int =
            if (i == 0) {
                0
            } else {
                fib(i - 2) + fib(i - 1)
            }
    /**
     * Re-enable the tests by removing the `!` prefix!
     */
    "fib" should {
        "return the nth fibonacci number" {
            persistentMapOf(
                Pair(1, 1),
                Pair(2, 1),
                Pair(3, 2),
                Pair(4, 3),
                Pair(5, 5),
                Pair(6, 8),
                Pair(7, 13),
                Pair(8, 21)
            ).forEach { (n, num) ->
                fib(n) shouldBe num
            }
        }
    }
})
