package uk.co.alistaironeill.wordle.domain.game

import uk.co.alistaironeill.wordle.domain.language.Dictionary

class GateKeepingGame(private val allowedWords: Set<String>, private val delegate: Game) : Game {
    override fun accept(word: String) =
        if (allowedWords.contains(word)) delegate.accept(word) else throw DisallowedWord(word)

    class DisallowedWord(val word: String) : Exception("[$word] is not in the dictionary")
}

fun Game.withAllowedWords(dictionary: Dictionary) = GateKeepingGame(dictionary.allWords, this)