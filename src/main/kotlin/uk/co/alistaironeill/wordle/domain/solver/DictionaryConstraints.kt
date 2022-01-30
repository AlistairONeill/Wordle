package uk.co.alistaironeill.wordle.domain.solver

import uk.co.alistaironeill.wordle.domain.language.Dictionary

//TODO: [AON] REMOVE
fun Dictionary.constrain(constraints: Constraints): Dictionary =
    Dictionary(
        solutions.filter(constraints::matches).toSet(),
        allWords
    )
