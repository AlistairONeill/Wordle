package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.A
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.B
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.C
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.D
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.E
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.constraints

class ConstraintsTest {
    @Test
    fun `basic combinations`() =
        expectThat("abcde").obeys {
            +constraints {
                0 Is A
                1 Is B
                2 Is C
                3 Is D
                4 Is E
            }

            -constraints {
                0 Is A
                1 Is B
                3 Is C
                3 Is D
                4 Is E
            }

            +constraints {
                A lte 1
                B gte 1
                2 Is C
                4 IsNot A
            }
        }

    @Test
    fun `multiples of letters`() =
        expectThat("aaabb").obeys {
            +constraints {
                A gte 2
                B gte 2
            }

            +constraints {
                A gte 3
                B lte 2
            }

            -constraints {
                A gte 4
                B gte 1
            }

            +constraints {
                A lte 3
                B lte 2
            }

            -constraints {
                A gte 1
                B lte 1
            }
        }

    @Test
    fun `can simplify AtLeast constraints`() {
        constraints {
            A gte 2
            A gte 4
        }
            .simplify()
            .let(::expectThat)
            .isEqualTo(
                constraints {
                    A gte 4
                }
            )

        constraints {
            A gte 2
            A gte 3
            A gte 4
            3 Is B
        }
            .simplify()
            .let(::expectThat)
            .isEqualTo(
                constraints {
                    A gte 4
                    3 Is B
                }
            )
    }

    @Test
    fun `can simplify AtMost constraints`() {
        constraints {
            A lte 3
            A lte 4
        }
            .simplify()
            .let(::expectThat)
            .isEqualTo(
                constraints {
                    A lte 3
                }
            )

        constraints {
            A lte 3
            A lte 4
            A lte 2
            5 Is B
        }
            .simplify()
            .let(::expectThat)
            .isEqualTo(
                constraints {
                    A lte 2
                    5 Is B
                }
            )
    }
}