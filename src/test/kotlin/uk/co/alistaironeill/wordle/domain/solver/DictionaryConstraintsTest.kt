package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.Letter.C
import uk.co.alistaironeill.wordle.domain.language.RealDictionary
import uk.co.alistaironeill.wordle.domain.language.word

class DictionaryConstraintsTest {
    private val abcde = "abcde".word
    private val aaabc = "aaabc".word
    private val acbde = "acbde".word
    private val acdeb = "acdeb".word

    @Test
    fun `constraints are applied to the possible solutions`() =
        expectThat(
            RealDictionary(
                setOf(abcde, aaabc),
                setOf(abcde, acbde, acdeb)
            )
        ) {
            get {
                constrain(
                    constraints {
                        2 Is C
                    }
                )
            }.isEqualTo(
                RealDictionary(
                    setOf(abcde),
                    setOf(abcde, acbde, acdeb)
                )
            )
        }

    fun `constraints are not applied to all words`() =
        expectThat(
            RealDictionary(
                setOf(abcde, aaabc),
                setOf(abcde, acbde, acdeb)
            )
        ) {
            get {
                constrain(
                    constraints {
                        2 IsNot C
                    }
                )
            }.isEqualTo(
                RealDictionary(
                    setOf(abcde),
                    setOf(abcde, acbde, acdeb)
                )
            )
        }


}