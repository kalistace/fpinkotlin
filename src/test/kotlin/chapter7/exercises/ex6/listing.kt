package chapter7.exercises.ex6

import chapter7.exercises.ex5.Listing.sequence
import chapter7.sec3.Par
import chapter7.sec4.map
import chapter7.solutions.sol4.lazyUnit

object Listing {
    //tag::init[]
    fun <A> parFilter(
        sa: List<A>,
        f: (A) -> Boolean
    ): Par<List<A>> {
        val pars = sa.map { lazyUnit { it } }
        return map(sequence(pars)) {
            it.flatMap { a -> if (f(a)) listOf(a) else emptyList() }
        }
    }
//end::init[]
}
