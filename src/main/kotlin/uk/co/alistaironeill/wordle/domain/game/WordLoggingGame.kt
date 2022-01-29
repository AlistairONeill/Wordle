package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.language.Word

class WordLoggingGame(
    private val delegate: Game
): Game {
    val words = ArrayList<Word>()

    override fun accept(word: Word) = delegate.accept(word).also { words.add(word) }
}