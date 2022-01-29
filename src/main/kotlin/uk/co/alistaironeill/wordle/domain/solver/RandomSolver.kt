package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.game.GameOutput
import uk.co.alistaironeill.wordle.domain.game.GameOutput.Result
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.Word
import uk.co.alistaironeill.wordle.domain.solver.ConstraintFinder.Companion.findConstraints

class RandomSolver(private val initialDictionary: Dictionary) : Solver {
    override fun solve(game: Game) = Instance(initialDictionary, game).solve()

    private class Instance(
        private var dictionary: Dictionary,
        private val game: Game
    ) {
        fun solve(): Word {
            while (true) {
                val word = dictionary.solutions.random()
                when (val result = game.accept(word)) {
                    GameOutput.InvalidInput -> throw RuntimeException("?!")
                    is Result -> handle(word, result)
                    GameOutput.Victory -> return word
                }
            }
        }

        private fun handle(word: Word, result: Result) {
            dictionary = dictionary.constrain(findConstraints(word, result))
        }
    }
}