package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.language.Word

class StubGame(var output: GameOutput): Game {
    override fun accept(word: Word): GameOutput = output
}