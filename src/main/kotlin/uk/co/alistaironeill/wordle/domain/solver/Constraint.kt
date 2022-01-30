package uk.co.alistaironeill.wordle.domain.solver


sealed interface Constraint {
    fun matches(word: String): Boolean

    data class Not(val letter: Char, val index: Int): Constraint {
        override fun matches(word: String) = word[index] != letter
    }

    data class Is(val letter: Char, val index: Int): Constraint {
        override fun matches(word: String) = word[index] == letter
    }

    data class AtLeast(val letter: Char, val count: Int): Constraint {
        override fun matches(word: String) = word.count{ it == letter} >= count
    }

    data class AtMost(val letter: Char, val count: Int): Constraint {
        override fun matches(word: String) = word.count { it == letter } <= count
    }
}
