package chapter8.exercises.ex12

import chapter8.RNG
import chapter8.State

data class SGen<A>(val forSize: (Int) -> Gen<A>)

data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =
            Gen(State.sequence(List(n) { ga.sample }))
    }

    //tag::init[]
    fun listOf(): SGen<List<A>> = SGen { i -> listOfN(i, this) }
    //end::init[]
}
