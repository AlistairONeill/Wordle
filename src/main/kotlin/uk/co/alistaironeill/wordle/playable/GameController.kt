package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.game.Result
import uk.co.alistaironeill.wordle.domain.game.ResultValue
import uk.co.alistaironeill.wordle.domain.language.word

class GameController(private val game: Game) {
    fun accept(input: String): String? =
        try {
            input.word
                .let(game::accept)
                ?.desc()
        } catch (e: Exception) {
            e.localizedMessage
        }

    private fun Result.desc() = values.map(ResultValue::symbol).joinToString("")
}

private fun ResultValue.symbol() =
    when (this) {
        ResultValue.GREY -> '.'
        ResultValue.YELLOW -> '?'
        ResultValue.GREEN -> '!'
    }