package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import uk.co.alistaironeill.wordle.domain.game.ResultValue.*
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.word

class RealGameTest {
    private val acorn = "acorn".word
    private val adept = "adept".word

    private val realDictionary = Dictionary(
        setOf(acorn),
        setOf(
            acorn,
            adept
        )
    )

    @Nested
    inner class SpecialReturns {
        private val game = RealGame(acorn)

        @Test
        fun `returns a win when you get the solution`() =
            expectThat(game) {
                accepts {
                    "acorn" returning null
                }
            }
    }

    @Nested
    inner class HintCalculations {
        private val game = RealGame("abcde".word)

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

        @Test
        fun `does not over yellow the results when there is a green`() =
            expectThat(game) {
                accepts {
                    "aafff" returning listOf(GREEN, GREY, GREY, GREY, GREY)
                    "afaff" returning listOf(GREEN, GREY, GREY, GREY, GREY)
                    "affaf" returning listOf(GREEN, GREY, GREY, GREY, GREY)
                    "afffa" returning listOf(GREEN, GREY, GREY, GREY, GREY)

                    "bbfff" returning listOf(GREY, GREEN, GREY, GREY, GREY)
                    "fbbff" returning listOf(GREY, GREEN, GREY, GREY, GREY)
                    "fbfbf" returning listOf(GREY, GREEN, GREY, GREY, GREY)
                    "fbffb" returning listOf(GREY, GREEN, GREY, GREY, GREY)

                    "cfcff" returning listOf(GREY, GREY, GREEN, GREY, GREY)
                    "fccff" returning listOf(GREY, GREY, GREEN, GREY, GREY)
                    "ffccf" returning listOf(GREY, GREY, GREEN, GREY, GREY)
                    "ffcfc" returning listOf(GREY, GREY, GREEN, GREY, GREY)

                    "dffdf" returning listOf(GREY, GREY, GREY, GREEN, GREY)
                    "fdfdf" returning listOf(GREY, GREY, GREY, GREEN, GREY)
                    "ffddf" returning listOf(GREY, GREY, GREY, GREEN, GREY)
                    "fffdd" returning listOf(GREY, GREY, GREY, GREEN, GREY)

                    "efffe" returning listOf(GREY, GREY, GREY, GREY, GREEN)
                    "feffe" returning listOf(GREY, GREY, GREY, GREY, GREEN)
                    "ffefe" returning listOf(GREY, GREY, GREY, GREY, GREEN)
                    "fffee" returning listOf(GREY, GREY, GREY, GREY, GREEN)
                }
            }

        @Test
        fun `does not over yellow the results when there is a single instance`() =
            expectThat(game) {
                accepts {
                    "faaff" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "fafaf" returning listOf(GREY, YELLOW, GREY, GREY, GREY)
                    "faffa" returning listOf(GREY, YELLOW, GREY, GREY, GREY)

                    "bfbff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "bffbf" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "bfffb" returning listOf(YELLOW, GREY, GREY, GREY, GREY)

                    "ccfff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "cffcf" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "cfffc" returning listOf(YELLOW, GREY, GREY, GREY, GREY)

                    "ddfff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "dfdff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "dfffd" returning listOf(YELLOW, GREY, GREY, GREY, GREY)

                    "eefff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "efeff" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                    "effef" returning listOf(YELLOW, GREY, GREY, GREY, GREY)
                }
            }

        @Test
        fun `handles multiple instances correctly`() =
            expectThat(RealGame("aaabb".word)) {
                accepts {
                    "aafaa" returning listOf(GREEN, GREEN, GREY, YELLOW, GREY)
                    "ababa" returning listOf(GREEN, YELLOW, GREEN, GREEN, YELLOW)
                    "abbba" returning listOf(GREEN, YELLOW, GREY, GREEN, YELLOW)
                }
            }
    }
}