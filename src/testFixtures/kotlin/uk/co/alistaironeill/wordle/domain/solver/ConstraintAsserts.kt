package uk.co.alistaironeill.wordle.domain.solver

import strikt.api.DescribeableBuilder
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import uk.co.alistaironeill.wordle.domain.language.word

fun DescribeableBuilder<out Constraint>.matches(block: ConstraintContext.() -> Unit) =
    ConstraintContext(this).block()

class ConstraintContext(private val builder: DescribeableBuilder<out Constraint>) {
    operator fun String.unaryPlus() {
        builder.get { matches(word) }.isTrue()
    }

    operator fun String.unaryMinus() {
        builder.get { matches(word) }.isFalse()
    }
}