package uk.co.alistaironeill.wordle.domain.solver

import strikt.api.Assertion
import strikt.assertions.isFalse
import strikt.assertions.isTrue

fun Assertion.Builder<String>.obeys(block: ConstraintsContext.() -> Unit) =
    ConstraintsContext(this).block()

class ConstraintsContext(private val builder: Assertion.Builder<String>) {
    operator fun Constraints.unaryPlus() {
        builder.get { matches(this) }.isTrue()
    }

    operator fun Constraints.unaryMinus() {
        builder.get { matches(this) }.isFalse()
    }
}