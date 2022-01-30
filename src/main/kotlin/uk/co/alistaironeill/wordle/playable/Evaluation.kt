package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.performance.evaluate
import uk.co.alistaironeill.wordle.domain.solver.RandomSolver
import java.io.File

fun main() {
    val dictionary = Dictionary.fromFolder(File("data"))
    val solver = RandomSolver(dictionary)
    evaluate(dictionary, solver).let(::println)
}