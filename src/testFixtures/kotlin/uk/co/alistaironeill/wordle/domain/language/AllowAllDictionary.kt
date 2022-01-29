package uk.co.alistaironeill.wordle.domain.language

class AllowAllDictionary : Dictionary {
    override fun allowed(word: Word) = true
    override fun isPossibleSolution(word: Word) = true
}