package uk.co.alistaironeill.wordle.domain.game

class WordLoggingGame(
    private val delegate: Game
): Game {
    private val _words = ArrayList<String>()
    val words get(): List<String> = _words
    override fun accept(word: String) = delegate.accept(word).also { _words.add(word) }
}