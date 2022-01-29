package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.game.GameOutput
import uk.co.alistaironeill.wordle.domain.language.Letter
import uk.co.alistaironeill.wordle.domain.language.Word
import uk.co.alistaironeill.wordle.domain.language.word

class GameController(private val game: Game) {
    fun accept(input: String): String {
        val word = parse(input) ?: return "Failed to parse [$input]"
        return when (val output = game.accept(word)) {
            GameOutput.InvalidInput -> "${word.desc()} is not a valid word"
            is GameOutput.Result -> output.desc()
            GameOutput.Victory -> throw RuntimeException()
        }
    }

    private fun parse(input: String): Word? =
        try {
            input.word
        } catch (e: Exception) {
            null
        }

    private fun Word.desc() = letters.map(Letter::name).joinToString("")
    private fun GameOutput.Result.desc() = values.map(GameOutput.ResultValue::symbol).joinToString("")
}

private fun GameOutput.ResultValue.symbol() =
    when (this) {
        GameOutput.ResultValue.GREY -> '.'
        GameOutput.ResultValue.YELLOW -> '?'
        GameOutput.ResultValue.GREEN -> '!'
    }