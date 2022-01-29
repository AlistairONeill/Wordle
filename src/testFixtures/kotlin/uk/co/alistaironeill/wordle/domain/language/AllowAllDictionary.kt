package uk.co.alistaironeill.wordle.domain.language

object AllowAllDictionary : Dictionary {
    override fun allowed(word: Word) = true
    override fun isPossibleSolution(word: Word) = true
}