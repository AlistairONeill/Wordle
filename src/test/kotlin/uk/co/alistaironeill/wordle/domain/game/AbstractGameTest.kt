package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

abstract class AbstractGameTest {
    protected val stubGame = StubGame()
    abstract val game : Game

    abstract fun randomAllowedWord(): String

    @Test
    fun `results get passed through correctly`() {
        fun assertPassedThrough(result: Result?) {
            stubGame.result = result
            randomAllowedWord()
                .let(game::accept)
                .let(::expectThat)
                .isEqualTo(result)
        }

        repeat(5) {
            assertPassedThrough(randomResult)
        }

        assertPassedThrough(null)
    }
}