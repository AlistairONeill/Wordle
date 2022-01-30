package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.game.ResultValue.*
import uk.co.alistaironeill.wordle.domain.language.Letter
import uk.co.alistaironeill.wordle.domain.language.Word

class RealGame(
    private val solution: Word
) : Game {

    //TODO: [AON] Collapse
    override fun accept(word: Word): Result? =
        if (word == solution) null else findOutput(word)

    private fun findOutput(word: Word): Result {
        val solution = solution.mutable()
        val output = solution.factoryOutput()

        for (i in 0 until word.size) {
            if (solution.isGreen(word[i], i)) {
                output[i] = GREEN
            }
        }

        for (i in 0 until word.size) {
            if (output[i] == null && solution.isYellow(word[i])) {
                output[i] = YELLOW
            }
        }

        return output.build()
    }

    private class MutableWord(val letters: Array<Letter?>) {
        fun factoryOutput() = MutableResult(arrayOfNulls(letters.size))

        fun isGreen(letter: Letter, index: Int): Boolean =
            if (letters[index] == letter) {
                letters[index] = null
                true
            } else {
                false
            }

        fun isYellow(letter: Letter): Boolean {
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

    private fun Word.mutable() = MutableWord(letters.toTypedArray())
}