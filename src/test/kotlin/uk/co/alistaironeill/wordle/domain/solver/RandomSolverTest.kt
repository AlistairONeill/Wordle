package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.game.RealGame
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import java.io.File

class RandomSolverTest {

    private fun assertCanSolve(dictionary: Dictionary) {
        val solution = dictionary.getRandomSolution()
        val game = RealGame(solution)
        RandomSolver(dictionary)
            .solve(game)
            .let(::expectThat)
            .isEqualTo(solution)
    }

    @Test
    fun `random solver can solve simple game`() =
        Dictionary(
            setOf("abcde", "bskde", "absej"),
            setOf("abcde", "bskde", "absej", "askdn", "asjfa")
        ).let(::assertCanSolve)

    @Test
    fun `random solver can solve an actual game`() =
        Dictionary.fromFolder(File("data"))
            .let(::assertCanSolve)
}