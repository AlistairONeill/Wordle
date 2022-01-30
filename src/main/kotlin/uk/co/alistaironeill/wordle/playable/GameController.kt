package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.game.GateKeepingGame.DisallowedWord
import uk.co.alistaironeill.wordle.domain.game.Result
import uk.co.alistaironeill.wordle.domain.game.ResultValue

class GameController(private val game: Game) {
    fun accept(input: String): String? =
        try {
            game.accept(input)?.desc()
        } catch (e: DisallowedWord) {
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