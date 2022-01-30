package uk.co.alistaironeill.wordle.domain.game

class WordLoggingGame(
    private val delegate: Game
): Game {
    val words = ArrayList<String>()

    override fun accept(word: String) = delegate.accept(word).also { words.add(word) }
}