package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.RealDictionary

fun Dictionary.constrain(constraints: Constraints): RealDictionary =
    RealDictionary(
        solutions.filter(constraints::matches).toSet(),
        allWords
    )
