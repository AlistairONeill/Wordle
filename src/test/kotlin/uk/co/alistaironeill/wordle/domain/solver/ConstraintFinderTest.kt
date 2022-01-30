package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.game.Result
import uk.co.alistaironeill.wordle.domain.game.ResultValue
import uk.co.alistaironeill.wordle.domain.game.ResultValue.*
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.A
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.B
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.constraints
import uk.co.alistaironeill.wordle.domain.solver.ConstraintFinder.Companion.findConstraints

class ConstraintFinderTest {

    private fun findConstraints(input: String, vararg result: ResultValue) =
        findConstraints(input, Result(result.toList()))

    @Test
    fun `can find simple constraints`() {
        findConstraints("aaa", GREEN, GREEN, GREEN)
            .let(::expectThat)
            .isEqualTo(constraints {
                0 Is A
                1 Is A
                2 Is A
                A gte 3
            })

        findConstraints("aba", GREEN, GREEN, GREEN)
            .let(::expectThat)
            .isEqualTo(constraints {
                0 Is A
                1 Is B
                2 Is A
                A gte 2
                B gte 1
            })
    }

    @Test
    fun `can use Yellow to find constraints`() {
        findConstraints("aba", GREEN, YELLOW, YELLOW)
            .let(::expectThat)
            .isEqualTo(constraints {
                0 Is A
                1 IsNot B
                2 IsNot A
                A gte 2
                B gte 1
            })
    }

    @Test
    fun `can use Grey to find constraints`() {
        findConstraints("aba", GREEN, YELLOW, GREY)
            .let(::expectThat)
            .isEqualTo(constraints {
                0 Is A
                1 IsNot B
                2 IsNot A
                A gte 1
                B gte 1
                A lte 1
            })
    }
}