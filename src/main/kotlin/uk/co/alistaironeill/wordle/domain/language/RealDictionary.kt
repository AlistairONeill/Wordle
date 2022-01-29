package uk.co.alistaironeill.wordle.domain.language

import java.io.File

data class RealDictionary(
    private val solutions: Set<Word>,
    private val allWords: Set<Word>
) : Dictionary {
    companion object {
        private const val POSSIBLE_SOLUTIONS = "possibleSolutions.dat"
        private const val OTHER_ALLOWED_WORDS = "otherAllowedWords.dat"

        fun fromFolder(folder: File): RealDictionary {
            val possibleSolutions = fromFile(File(folder, POSSIBLE_SOLUTIONS))
            val otherWords = fromFile(File(folder, OTHER_ALLOWED_WORDS))
            return RealDictionary(
                possibleSolutions.toSet(),
                (possibleSolutions + otherWords).toSet()
            )
        }

        private fun fromFile(file: File) : List<Word> =
            file.readLines().map(String::word)
    }

    override fun allowed(word: Word): Boolean = allWords.contains(word)
    override fun isPossibleSolution(word: Word): Boolean = solutions.contains(word)
    fun getRandomSolution() = solutions.random()
}