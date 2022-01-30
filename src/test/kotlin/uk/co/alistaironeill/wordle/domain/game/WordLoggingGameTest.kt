package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.randomWord

class WordLoggingGameTest {
    private val stubGame = StubGame()
    private val game = WordLoggingGame(stubGame)

    @Test
    fun `words get logged`() {
        val words = List(100) { randomWord }
        words.forEach(game::accept)
        expectThat(game.words).isEqualTo(words)
    }

    @Test
    fun `results get passed through correctly`() {
        fun assertPassedThrough(result: Result?) {
            stubGame.result = result
            game.accept(randomWord)
                .let(::expectThat)
                .isEqualTo(result)
        }

        repeat(5) {
            assertPassedThrough(randomResult)
        }

        assertPassedThrough(null)
    }
}