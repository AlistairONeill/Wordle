package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.language.Letter

class ConstraintBuilder {
    internal val constraints = mutableSetOf<Constraint>()

    private operator fun Constraint.unaryPlus() = constraints.add(this)

    infix fun Int.Is(letter: Letter) = + Constraint.Is(letter, this)
    infix fun Int.IsNot(letter: Letter) = + Constraint.Not(letter, this)
    infix fun Letter.gte(count: Int) = + Constraint.AtLeast(this, count)
    infix fun Letter.lte(count: Int) = + Constraint.AtMost(this, count)

}

fun constraints(block: ConstraintBuilder.() -> Unit) =
    ConstraintBuilder().apply(block).constraints.let(::Constraints)