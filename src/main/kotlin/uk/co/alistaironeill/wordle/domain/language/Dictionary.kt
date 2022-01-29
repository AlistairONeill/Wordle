package uk.co.alistaironeill.wordle.domain.language

interface Dictionary {
    val solutions: Set<Word>
    val allWords: Set<Word>
    fun allowed(word: Word): Boolean
    fun isPossibleSolution(word: Word): Boolean
}
