package uk.co.alistaironeill.wordle.domain.language

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.Letter.*

class WordTest {
    private val acorn = Word(listOf(A, C, O, R, N))

    @Nested
    inner class Constructors {
        @Test
        fun `can create word from lowercase string`() {
            expectThat("acorn".word).isEqualTo(acorn)
        }

        @Test
        fun `can create word from uppercase string`() {
            expectThat("ACORN".word).isEqualTo(acorn)
        }

        @Test
        fun `can create word from mixed case string`() {
            expectThat("AcOrN".word).isEqualTo(acorn)
        }
    }
}