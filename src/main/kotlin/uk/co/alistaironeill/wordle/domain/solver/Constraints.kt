package uk.co.alistaironeill.wordle.domain.solver


data class Constraints(val constraints: Set<Constraint>) {
    fun matches(word: String) = constraints.fold(true) { acc, constraint -> acc && constraint.matches(word) }

    private fun doesNotMatch(word: String) = !matches(word)

    fun filter(set: MutableSet<String>) = set.removeIf(::doesNotMatch)
}
