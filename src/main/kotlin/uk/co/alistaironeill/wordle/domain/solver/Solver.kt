package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.game.Game

interface Solver {
    fun solve(game: Game): String
}