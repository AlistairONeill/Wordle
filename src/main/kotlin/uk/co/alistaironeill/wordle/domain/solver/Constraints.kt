package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.solver.Constraint.*


data class Constraints(val constraints: Set<Constraint>) {
    fun matches(word: String) = constraints.fold(true) { acc, constraint -> acc && constraint.matches(word) }

    private fun doesNotMatch(word: String) = !matches(word)

    fun filter(set: MutableSet<String>) = set.removeIf(::doesNotMatch)

    fun simplify() : Constraints =
        constraints.filter(::isNotSuperseded)
            .toSet()
            .let(::Constraints)


    private fun isNotSuperseded(constraint: Constraint) : Boolean =
        when (constraint) {
            is AtLeast -> constraints.none { it is AtLeast && it.letter == constraint.letter && it.count > constraint.count }
            is AtMost -> constraints.none { it is AtMost && it.letter == constraint.letter && it.count < constraint.count }
            is Is -> true
            is Not -> true
        }
}
