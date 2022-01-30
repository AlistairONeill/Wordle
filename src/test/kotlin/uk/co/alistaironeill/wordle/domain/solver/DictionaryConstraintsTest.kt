package uk.co.alistaironeill.wordle.domain.solver

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.C
import uk.co.alistaironeill.wordle.domain.solver.ConstraintBuilder.Companion.constraints

class DictionaryConstraintsTest {
    private val abcde = "abcde"
    private val aaabc = "aaabc"
    private val acbde = "acbde"
    private val acdeb = "acdeb"

    @Test
    fun `constraints are applied to the possible solutions`() =
        expectThat(
            Dictionary(
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
                Dictionary(
                    setOf(abcde),
                    setOf(abcde, acbde, acdeb)
                )
            )
        }

    @Test
    fun `constraints are not applied to all words`() =
        expectThat(
            Dictionary(
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
                Dictionary(
                    setOf(aaabc),
                    setOf(abcde, acbde, acdeb)
                )
            )
        }


}