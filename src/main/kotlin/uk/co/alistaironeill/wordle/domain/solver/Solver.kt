package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.game.Game
import uk.co.alistaironeill.wordle.domain.language.Word

interface Solver {
    fun solve(game: Game): Word
}