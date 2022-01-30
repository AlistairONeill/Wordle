package uk.co.alistaironeill.wordle.domain.game

interface Game {
    fun accept(word: String): Result?
}