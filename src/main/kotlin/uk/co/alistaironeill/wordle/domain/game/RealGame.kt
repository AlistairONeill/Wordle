package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.game.GameOutput.ResultValue.*
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
            when {
                letter == solution[index] -> GREEN
                solution.contains(letter) -> YELLOW
                else -> GREY
            }
        }.let(GameOutput::Result)
}