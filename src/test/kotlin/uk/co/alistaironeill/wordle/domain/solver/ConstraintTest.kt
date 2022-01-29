package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import uk.co.alistaironeill.wordle.domain.language.Letter.A
import uk.co.alistaironeill.wordle.domain.solver.Constraint.*

class ConstraintTest {
    @Test
    fun `The Is constraint works`() {
        expectThat(Is(A, 1)).matches {
            + "babbb"
            - "abbbb"
            + "aaaaa"
            - "bbbbb"
            - "abaaa"
        }

        expectThat(Is(A, 2)).matches {
            - "babbb"
            - "abbbb"
            + "aaaaa"
            - "bbbbb"
            + "abaaa"
        }
    }

    @Test
    fun `The Not constraint works`() {
        expectThat(Not(A, 1)).matches {
            + "abaaa"
            + "bbbbb"
            - "babbb"
            - "aaaaa"
        }

        expectThat(Not(A, 2)).matches {
            - "abaaa"
            + "bbbbb"
            + "babbb"
            - "aaaaa"
        }
    }

    @Test
    fun `The AtLeast constraint works`() {
        expectThat(AtLeast(A, 1)).matches {
            - "bbbbb"
            + "aaaaa"
            + "abbbb"
            + "bbbaa"
            + "aabba"
            + "bbabb"
        }

        expectThat(AtLeast(A, 2)).matches {
            - "bbbbb"
            + "aaaaa"
            - "abbbb"
            + "bbbaa"
            + "aabba"
            - "bbabb"
        }
    }

    @Test
    fun `The AtMost constraint works`() {
        expectThat(AtMost(A, 1)).matches {
            + "bbbbb"
            - "aaaaa"
            + "abbbb"
            - "bbbaa"
            - "aabba"
            + "bbabb"
        }

        expectThat(AtMost(A, 2)).matches {
            + "bbbbb"
            - "aaaaa"
            + "abbbb"
            + "bbbaa"
            - "aabba"
            + "bbabb"
        }
    }
}

