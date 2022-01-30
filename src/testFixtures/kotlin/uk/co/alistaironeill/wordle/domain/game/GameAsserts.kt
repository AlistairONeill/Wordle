package uk.co.alistaironeill.wordle.domain.game

import strikt.api.Assertion
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.word

fun Assertion.Builder<out Game>.accepts(block: GameContext.() -> Unit) =
    GameContext(this).block()

class GameContext(private val builder: Assertion.Builder<out Game>) {
    infix fun String.returning(expected: Result?) {
        builder.get { accept(word) }.isEqualTo(expected)
    }

    infix fun String.returning(expected: List<ResultValue>) = this returning Result(expected)
}