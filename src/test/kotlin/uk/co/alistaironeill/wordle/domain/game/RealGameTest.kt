package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import uk.co.alistaironeill.wordle.domain.game.GameOutput.*
import uk.co.alistaironeill.wordle.domain.game.GameOutput.ResultValue.*
import uk.co.alistaironeill.wordle.domain.language.AllowAllDictionary
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
                accepts {
                    "wrong" returning InvalidInput
                }
            }

        @Test
        fun `returns a win when you get the solution`() =
            expectThat(game) {
                accepts {
                    "acorn" returning Victory
                }
            }
    }

    @Nested
    inner class HintCalculations {
        private val game = RealGame(AllowAllDictionary, "abcde".word)

        @Test
        fun `returns green when a letter is in the correct spot`() =
            expectThat(game) {
                accepts {
                    "affff" returning listOf(GREEN, GREY, GREY, GREY, GREY)
                    "fbfff" returning listOf(GREY, GREEN, GREY, GREY, GREY)
                    "ffcff" returning listOf(GREY, GREY, GREEN, GREY, GREY)
                    "fffdf" returning listOf(GREY, GREY, GREY, GREEN, GREY)
                    "ffffe" returning listOf(GREY, GREY, GREY, GREY, GREEN)
                }
            }

        @Test
        fun `returns yellow when a letter is correct but in the wrong place`() =
            expectThat(game) {
                accepts {
                    "fafff" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "ffaff" returning listOf(GREY, GREY, YELLOW, GREY, GREY)
                    "fffaf" returning listOf(GREY, GREY, GREY, YELLOW, GREY)
                    "ffffa" returning listOf(GREY, GREY, GREY, GREY, YELLOW)

                    "bffff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "ffbff" returning listOf(GREY, GREY, YELLOW, GREY, GREY)
                    "fffbf" returning listOf(GREY, GREY, GREY, YELLOW, GREY)
                    "ffffb" returning listOf(GREY, GREY, GREY, GREY, YELLOW)

                    "cffff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "fcfff" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "fffcf" returning listOf(GREY, GREY, GREY, YELLOW, GREY)
                    "ffffc" returning listOf(GREY, GREY, GREY, GREY, YELLOW)

                    "dffff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "fdfff" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "ffdff" returning listOf(GREY, GREY, YELLOW, GREY, GREY)
                    "ffffd" returning listOf(GREY, GREY, GREY, GREY, YELLOW)

                    "effff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "fefff" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "ffeff" returning listOf(GREY, GREY, YELLOW, GREY, GREY)
                    "fffef" returning listOf(GREY, GREY, GREY, YELLOW, GREY)
                }
            }
    }
}