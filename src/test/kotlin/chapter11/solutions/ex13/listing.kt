package chapter11.solutions.ex13

import arrow.Kind
import chapter11.Monad

interface Listing<F, A> : Monad<F> {

    //tag::initA[]
    val f: (A) -> Kind<F, A>
    val g: (A) -> Kind<F, A>
    val x: Kind<F, A>
    val y: Kind<F, Kind<F, Kind<F, A>>>
    val z: (Kind<F, Kind<F, A>>) -> Kind<F, Kind<F, A>>
    //end::initA[]

    fun associative() {
        //tag::init1[]
        flatMap(flatMap(x, f), g) ==
            flatMap(x) { a -> flatMap(f(a), g) }
        //end::init1[]

        //tag::init2[]
        flatMap(flatMap(y, z)) { b -> b } ==
            flatMap(y) { a -> flatMap(z(a)) { b -> b } }

        flatMap(flatMap(y, z)) { it } ==
            flatMap(y) { a -> flatMap(a) { it } }
        //end::init2[]

        //tag::init3[]
        flatMap(join(y)) { it } ==
            flatMap(y) { join(it) }

        join(join(y)) ==
            flatMap(y) { join(it) }
        //end::init3[]

        //tag::init4[]
        join(join(y)) ==
            join(map(y) { join(it) })
        //end::init4[]

        //tag::init5[]
        join(unit(x)) ==
            join(map(x) { unit(it) })
        //end::init5[]
    }
}
