package uk.co.alistaironeill.wordle.domain.language

import java.io.File

interface Dictionary {
    fun allowed(word: Word): Boolean
    fun isPossibleSolution(word: Word): Boolean
}

