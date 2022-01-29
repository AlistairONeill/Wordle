package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import uk.co.alistaironeill.wordle.domain.language.Letter.*

class ConstraintsTest {
    @Test
    fun `basic combinations`() =
        expectThat("abcde").obeys {
            + constraints {
                0 Is A
                1 Is B
                2 Is C
                3 Is D
                4 Is E
            }

            - constraints {
                0 Is A
                1 Is B
                3 Is C
                3 Is D
                4 Is E
            }

            + constraints {
                A lte 1
                B gte 1
                2 Is C
                4 IsNot A
            }
        }

    @Test
    fun `multiples of letters`() =
        expectThat("aaabb").obeys {
            + constraints {
                A gte 2
                B gte 2
            }

            + constraints {
                A gte 3
                B lte 2
            }

            - constraints {
                A gte 4
                B gte 1
            }

            + constraints {
                A lte 3
                B lte 2
            }

            - constraints {
                A gte 1
                B lte 1
            }
        }
}