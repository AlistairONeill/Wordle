package uk.co.alistaironeill.wordle.domain.game

class StubGame(var result: Result? = null) : Game {
    override fun accept(word: String): Result? = result
}