package uk.co.alistaironeill.wordle.domain.performance

import uk.co.alistaironeill.wordle.domain.game.RealGame
import uk.co.alistaironeill.wordle.domain.game.WordLoggingGame
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.Word
import uk.co.alistaironeill.wordle.domain.solver.Solver

data class Stats(
    val bestSolve: Int,
    val worstSolve: Int,
    val average: Double
)

fun evaluate(dictionary: Dictionary, solver: Solver) =
    dictionary
        .solutions
        .asSequence()
        .map { solution -> RealGame(dictionary, solution) }
        .map(::WordLoggingGame)
        .onEach(solver::solve)
        .map(WordLoggingGame::words)
        .map(List<Word>::size)
        .toList()
        .toStats()

private fun List<Int>.toStats() =
    Stats(
        minOf { it },
        maxOf { it },
        average()
    )