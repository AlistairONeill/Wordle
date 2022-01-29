package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.language.Letter
import uk.co.alistaironeill.wordle.domain.language.Word

sealed interface Constraint {
    fun matches(word: Word): Boolean

    data class Not(val letter: Letter, val index: Int): Constraint {
        override fun matches(word: Word) = word[index] != letter
    }

    data class Is(val letter: Letter, val index: Int): Constraint {
        override fun matches(word: Word) = word[index] == letter
    }

    data class AtLeast(val letter: Letter, val count: Int): Constraint {
        override fun matches(word: Word) = word.letters.count{it == letter} >= count
    }

    data class AtMost(val letter: Letter, val count: Int): Constraint {
        override fun matches(word: Word) = word.letters.count { it == letter } <= count
    }
}
