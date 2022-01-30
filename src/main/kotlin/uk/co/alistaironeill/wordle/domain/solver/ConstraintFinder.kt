package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.game.Result
import uk.co.alistaironeill.wordle.domain.game.ResultValue.GREEN
import uk.co.alistaironeill.wordle.domain.game.ResultValue.GREY
import uk.co.alistaironeill.wordle.domain.language.Word
import uk.co.alistaironeill.wordle.domain.solver.Constraint.*

class ConstraintFinder private constructor(
    private val word: Word,
    private val result: Result
) {
    companion object {
        fun findConstraints(word: Word, result: Result): Constraints =
            ConstraintFinder(word, result).find()
    }

    private val map = word.letters
        .zip(result.values)
        .groupBy { it.first }
        .mapValues { (_, value) -> value.map { it.second } }

    private fun find() =
        listOf(
            findIsAndNotConstraints(),
            findGteConstraints(),
            findLteConstraints()
        )
            .flatten()
            .toSet()
            .let(::Constraints)

    private fun findIsAndNotConstraints(): List<Constraint> =
        word.letters.mapIndexed { index, letter ->
            when (result[index]) {
                GREEN -> Is(letter, index)
                else -> Not(letter, index)
            }
        }

    private fun findGteConstraints(): List<Constraint> =
        map.map { (letter, results) ->
            AtLeast(letter, results.count { it != GREY })
        }

    private fun findLteConstraints(): List<Constraint> =
        map.filter { (_, results) ->
            results.contains(GREY)
        }.map { (letter, results) ->
            AtMost(letter, results.count { it != GREY })
        }
}