package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.RealDictionary
import uk.co.alistaironeill.wordle.domain.language.word

class RealGameTest {
    private val acorn = "acorn".word
    private val adept = "adept".word
    private val wrong = "wrong".word

    private val realDictionary = RealDictionary(
        setOf(acorn),
        setOf(
            acorn,
            adept
        )
    )

    @Nested
    inner class Validation {
        @Test
        fun `cannot create a game where the solution isn't in the solutions dictionary`() {
            expectThrows<RuntimeException> {
                RealGame(realDictionary, adept)
            }

            expectThrows<RuntimeException> {
                RealGame(realDictionary, wrong)
            }

            RealGame(realDictionary, acorn)
        }
    }

    @Nested
    inner class SpecialReturns {
        private val game = RealGame(realDictionary, acorn)

        @Test
        fun `only accepts valid words`() =
            expectThat(game) {
                get { accept(wrong) }
                    .isEqualTo(GameOutput.InvalidInput)
            }

        @Test
        fun `returns a win when you get the solution`() =
            expectThat(game) {
                get { accept(acorn) }
                    .isEqualTo(GameOutput.Victory)
            }
    }
}