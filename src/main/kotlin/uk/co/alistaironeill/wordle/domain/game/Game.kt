package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.language.Word

interface Game {
    fun accept(word: Word): Result?
}