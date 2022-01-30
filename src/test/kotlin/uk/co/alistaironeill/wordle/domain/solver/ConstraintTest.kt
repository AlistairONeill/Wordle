package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import uk.co.alistaironeill.wordle.domain.solver.Constraint.*

class ConstraintTest {
    @Test
    fun `The Is constraint works`() {
        expectThat(Is('a', 1)).matches {
            + "babbb"
            - "abbbb"
            + "aaaaa"
            - "bbbbb"
            - "abaaa"
        }

        expectThat(Is('a', 2)).matches {
            - "babbb"
            - "abbbb"
            + "aaaaa"
            - "bbbbb"
            + "abaaa"
        }
    }

    @Test
    fun `The Not constraint works`() {
        expectThat(Not('a', 1)).matches {
            + "abaaa"
            + "bbbbb"
            - "babbb"
            - "aaaaa"
        }

        expectThat(Not('a', 2)).matches {
            - "abaaa"
            + "bbbbb"
            + "babbb"
            - "aaaaa"
        }
    }

    @Test
    fun `The AtLeast constraint works`() {
        expectThat(AtLeast('a', 1)).matches {
            - "bbbbb"
            + "aaaaa"
            + "abbbb"
            + "bbbaa"
            + "aabba"
            + "bbabb"
        }

        expectThat(AtLeast('a', 2)).matches {
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
        expectThat(AtMost('a', 1)).matches {
            + "bbbbb"
            - "aaaaa"
            + "abbbb"
            - "bbbaa"
            - "aabba"
            + "bbabb"
        }

        expectThat(AtMost('a', 2)).matches {
            + "bbbbb"
            - "aaaaa"
            + "abbbb"
            + "bbbaa"
            - "aabba"
            + "bbabb"
        }
    }
}

