package uk.co.alistaironeill.wordle.domain.game

import org.junit.jupiter.api.Test
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.game.GateKeepingGame.DisallowedWord
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.language.randomWord

class GateKeepingGameTest : AbstractGameTest() {
    private val dictionary = Dictionary(
        emptySet(),
        List(5) { randomWord }.toSet()
    )

    override val game = stubGame.withAllowedWords(dictionary)
    override fun randomAllowedWord() = dictionary.allWords.random()

    @Test
    fun `allows known words`() =
        dictionary
            .allWords
            .forEach(game::accept)

    @Test
    fun `throws on bad words`() =
        repeat(5) {
            val word = randomWord
            expectThrows<DisallowedWord> { game.accept(word) }
                .get(DisallowedWord::word)
                .isEqualTo(word)
        }

}