package chapter8.exercises.ex6

import chapter8.RNG
import chapter8.State

//tag::init[]
data class Gen<A>(val sample: State<RNG, A>) {

    companion object {
        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> =
            Gen(State.sequence(List(n) { ga.sample }))

        fun <A> listOfN(gn: Gen<Int>, ga: Gen<A>): Gen<List<A>> =
            gn.flatMap { listOfN(it, ga) }
    }

    fun <B> flatMap(f: (A) -> Gen<B>): Gen<B> =
        Gen(this.sample.flatMap { f(it).sample })
}
//end::init[]
