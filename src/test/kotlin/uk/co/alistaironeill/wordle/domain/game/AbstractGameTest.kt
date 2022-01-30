package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.randomWord

abstract class AbstractGameTest {
    protected val stubGame = StubGame()
    abstract val game : Game

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