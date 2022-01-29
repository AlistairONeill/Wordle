package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.game.GameOutput.ResultValue.*
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.Letter
import uk.co.alistaironeill.wordle.domain.language.Word

class RealGame(
    private val dictionary: Dictionary,
    private val solution: Word
) : Game {
    init {
        if (!dictionary.isPossibleSolution(solution)) {
            throw RuntimeException("SOLUTION ISN'T ALLOWED!")
        }
    }

    override fun accept(word: Word): GameOutput =
        when {
            word == solution -> GameOutput.Victory
            !dictionary.allowed(word) -> GameOutput.InvalidInput
            else -> findOutput(word)
        }

    private fun findOutput(word: Word): GameOutput {
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

    private class MutableResult(val values: Array<GameOutput.ResultValue?>) {
        operator fun get(index: Int) = values[index]
        operator fun set(index: Int, value: GameOutput.ResultValue) {
            values[index] = value
        }

        fun build(): GameOutput.Result = values.map { it ?: GREY }.let(GameOutput::Result)
    }

    private fun Word.mutable() = MutableWord(letters.toTypedArray())
}