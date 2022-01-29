package uk.co.alistaironeill.wordle.domain.language

object AllowAllDictionary : Dictionary {
    override val solutions get() = TODO()
    override val allWords get() = TODO()
    override fun allowed(word: Word) = true
    override fun isPossibleSolution(word: Word) = true
}