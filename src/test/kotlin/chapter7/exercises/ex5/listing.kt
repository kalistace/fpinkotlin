package chapter7.exercises.ex5

import chapter7.solutions.sol5.Pars
import chapter7.solutions.sol5.Pars.head
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

typealias Par<A> = (ExecutorService) -> Future<A>

object Listing {
    //tag::init1[]
    val <T> List<T>.head: T
        get() = first()

    val <T> List<T>.tail: List<T>
        get() = this.drop(1)

    val Nil = listOf<Nothing>()

    fun <A> sequence1(ps: List<Par<A>>): Par<List<A>> =
        when (ps) {
            Nil -> Pars.unit(Nil)
            else -> Pars.map2(
                ps.head,
                sequence1(ps.tail)
            ) { a: A, b: List<A> ->
                listOf(a) + b
            }
        }
    //end::init1[]
}
