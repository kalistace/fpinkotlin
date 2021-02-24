package chapter8.exercises.ex5

import chapter8.RNG
import chapter8.State
import chapter8.nextBoolean

data class Gen<A>(val sample: State<RNG, A>) {
    companion object {

        //tag::init[]
        fun <A> unit(a: A): Gen<A> = Gen(State.unit(a))

        fun boolean(): Gen<Boolean> =
            Gen(State { rng -> nextBoolean(rng) })

        fun <A> listOfN(n: Int, ga: Gen<A>): Gen<List<A>> {
            val listOfStates = List(n) { ga.sample }
            return Gen(State.sequence(listOfStates))
        }
        //end::init[]
    }
}
