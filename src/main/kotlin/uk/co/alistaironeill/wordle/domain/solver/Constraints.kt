package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.language.Word

data class Constraints(val constraints: Set<Constraint>) {
    fun matches(word: Word) =
        constraints.fold(true) { acc, constraint -> acc && constraint.matches(word) }
}