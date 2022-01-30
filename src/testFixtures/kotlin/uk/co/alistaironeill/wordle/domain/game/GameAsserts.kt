package uk.co.alistaironeill.wordle.domain.game

import strikt.api.Assertion
import strikt.assertions.isEqualTo

fun Assertion.Builder<out Game>.accepts(block: GameContext.() -> Unit) =
    GameContext(this).block()

class GameContext(private val builder: Assertion.Builder<out Game>) {
    infix fun String.returning(expected: Result?) {
        val word = this
        builder.get { accept(word) }.isEqualTo(expected)
    }

    infix fun String.returning(expected: List<ResultValue>) = this returning Result(expected)
}