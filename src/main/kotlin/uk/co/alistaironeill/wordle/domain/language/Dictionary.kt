package uk.co.alistaironeill.wordle.domain.language

import java.io.File

data class Dictionary(
    val solutions: Set<String>,
    val allWords: Set<String>
) {
    companion object {
        private const val POSSIBLE_SOLUTIONS = "possibleSolutions.dat"
        private const val OTHER_ALLOWED_WORDS = "otherAllowedWords.dat"

        fun fromFolder(folder: File): Dictionary {
            val possibleSolutions = fromFile(File(folder, POSSIBLE_SOLUTIONS))
            val otherWords = fromFile(File(folder, OTHER_ALLOWED_WORDS))
            return Dictionary(
                possibleSolutions.toSet(),
                (possibleSolutions + otherWords).toSet()
            )
        }

        private fun fromFile(file: File) : List<String> = file.readLines()
    }

    fun getRandomSolution() = solutions.random()
}