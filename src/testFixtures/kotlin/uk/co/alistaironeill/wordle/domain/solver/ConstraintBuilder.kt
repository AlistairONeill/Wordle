package uk.co.alistaironeill.wordle.domain.solver


class ConstraintBuilder {
    companion object {
        fun constraints(block: ConstraintBuilder.() -> Unit) =
            ConstraintBuilder().apply(block).constraints.let(::Constraints)

        const val A = 'a'
        const val B = 'b'
        const val C = 'c'
        const val D = 'd'
        const val E = 'e'
        const val F = 'f'
    }

    internal val constraints = mutableSetOf<Constraint>()

    private operator fun Constraint.unaryPlus() = constraints.add(this)

    infix fun Int.Is(letter: Char) = + Constraint.Is(letter, this)
    infix fun Int.IsNot(letter: Char) = + Constraint.Not(letter, this)
    infix fun Char.gte(count: Int) = + Constraint.AtLeast(this, count)
    infix fun Char.lte(count: Int) = + Constraint.AtMost(this, count)

}