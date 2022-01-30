package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.game.ResultValue.*

class RealGame(
    private val solution: String
) : Game {

    //TODO: [AON] Collapse
    override fun accept(word: String): Result? =
        if (word == solution) null else findOutput(word)

    private fun findOutput(word: String): Result {
        val solution = solution.mutable()
        val output = solution.factoryOutput()

        for (i in word.indices) {
            if (solution.isGreen(word[i], i)) {
                output[i] = GREEN
            }
        }

        for (i in word.indices) {
            if (output[i] == null && solution.isYellow(word[i])) {
                output[i] = YELLOW
            }
        }

        return output.build()
    }

    private class MutableWord(val letters: Array<Char?>) {
        fun factoryOutput() = MutableResult(arrayOfNulls(letters.size))

        fun isGreen(letter: Char, index: Int): Boolean =
            if (letters[index] == letter) {
                letters[index] = null
                true
            } else {
                false
            }

        fun isYellow(letter: Char): Boolean {
            val index = letters.indexOf(letter)
            return if (index > -1) {
                letters[index] = null
                true
            } else {
                false
            }
        }
    }

    private class MutableResult(val values: Array<ResultValue?>) {
        operator fun get(index: Int) = values[index]
        operator fun set(index: Int, value: ResultValue) {
            values[index] = value
        }

        fun build(): Result = values.map { it ?: GREY }.let(::Result)
    }

    @Suppress("USELESS_CAST") // This is IntelliJ being silly
    private fun String.mutable() = map { it as Char? }.toTypedArray().let(::MutableWord)
}