package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.game.GameOutput.ResultValue.GREEN
import uk.co.alistaironeill.wordle.domain.game.GameOutput.ResultValue.GREY
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.Word

class RealGame(
    private val dictionary: Dictionary,
    private val solution: Word
): Game {
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

    private fun findOutput(word: Word): GameOutput =
        word.letters.mapIndexed { index, letter ->
            if (letter == solution[index]) {
                GREEN
            } else {
                GREY
            }
        }.let(GameOutput::Result)
}