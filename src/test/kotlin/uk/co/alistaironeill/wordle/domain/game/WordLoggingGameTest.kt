package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.randomWord

class WordLoggingGameTest : AbstractGameTest() {
    override val game = WordLoggingGame(stubGame)

    @Test
    fun `words get logged`() {
        val words = List(100) { randomWord }
        words.forEach(game::accept)
        expectThat(game.words).isEqualTo(words)
    }
}