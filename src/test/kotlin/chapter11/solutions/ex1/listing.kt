package chapter11.solutions.ex1

import arrow.Kind
import arrow.core.ForListK
import arrow.core.ForSequenceK
import arrow.core.ListK
import arrow.core.ListKOf
import arrow.core.SequenceK
import arrow.core.fix
import chapter10.ForList
import chapter10.ForOption
import chapter10.List
import chapter10.ListOf
import chapter10.Option
import chapter10.OptionOf
import chapter10.fix
import chapter11.ForPar
import chapter11.ForParser
import chapter11.Par
import chapter11.ParOf
import chapter11.Parser
import chapter11.ParserOf
import chapter11.fix
import chapter11.sec2.Monad

//tag::init1[]
object Monads {

    val parMonad = object : Monad<ForPar> {
        override fun <A> unit(a: A): ParOf<A> = Par.unit(a)

        override fun <A, B> flatMap(
            fa: ParOf<A>,
            f: (A) -> ParOf<B>
        ): ParOf<B> =
            Par.flatMap(fa.fix()) { f(it).fix() }
    }

    val parserMonad = object : Monad<ForParser> {
        override fun <A> unit(a: A): ParserOf<A> = Parser.succeed(a)

        override fun <A, B> flatMap(
            fa: ParserOf<A>,
            f: (A) -> ParserOf<B>
        ): ParserOf<B> =
            Parser.flatMap(fa.fix()) { f(it).fix() }
    }

    val optionMonad = object : Monad<ForOption> {
        override fun <A> unit(a: A): OptionOf<A> = TODO()

        override fun <A, B> flatMap(
            fa: OptionOf<A>,
            f: (A) -> OptionOf<B>
        ): OptionOf<B> =
            Option.flatMap(fa.fix()) { f(it).fix() }
    }

    val listMonad = object : Monad<ForList> {
        override fun <A> unit(a: A): ListOf<A> = List.empty()

        override fun <A, B> flatMap(
            fa: ListOf<A>,
            f: (A) -> ListOf<B>
        ): ListOf<B> =
            fa.fix().flatMap { f(it).fix() }
    }

    val listKMonad = object : Monad<ForListK> {
        override fun <A> unit(a: A): ListKOf<A> = ListK.empty()

        override fun <A, B> flatMap(
            fa: ListKOf<A>,
            f: (A) -> ListKOf<B>
        ): ListKOf<B> =
            fa.fix().flatMap(f)
    }

    val sequenceKMonad = object : Monad<ForSequenceK> {
        override fun <A> unit(a: A): Kind<ForSequenceK, A> =
            SequenceK.empty()

        override fun <A, B> flatMap(
            fa: Kind<ForSequenceK, A>,
            f: (A) -> Kind<ForSequenceK, B>
        ): Kind<ForSequenceK, B> {
            return fa.fix().flatMap(f)
        }
    }
}
//tag::init1[]
