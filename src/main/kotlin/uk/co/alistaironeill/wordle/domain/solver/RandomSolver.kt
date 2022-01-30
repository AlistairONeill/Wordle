package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.game.Result
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.solver.ConstraintFinder.Companion.findConstraints

class RandomSolver(private val dictionary: Dictionary) : Solver {
    override fun solve(game: Game) = Instance(dictionary.solutions, game).solve()

    private class Instance(
        solutions: Set<String>,
        private val game: Game
    ) {
        val solutions = solutions.toMutableSet()

        fun solve(): String {
            while (true) {
                val word = solutions.random()
                game.accept(word)
                    ?.let { handle(word, it) }
                    ?: return word
            }
        }

        private fun handle(word: String, result: Result) {
            findConstraints(word, result)
                .filter(solutions)
        }
    }
}