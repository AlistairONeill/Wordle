package uk.co.alistaironeill.wordle.domain.language

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import java.io.File

class RealDictionaryTest {
    @Nested
    inner class FromFolder {
        private val dictionary = RealDictionary.fromFolder(File("data"))

        private val acorn = "acorn".word
        private val abash = "abash".word

        @Test
        fun `solutions populated correctly`() {
            expectThat(dictionary) {
                get { isPossibleSolution(acorn) }.isTrue()
                get { isPossibleSolution(abash) }.isFalse()
            }
        }

        @Test
        fun `all words populated correctly`() {
            expectThat(dictionary) {
                get { allowed(acorn) }.isTrue()
                get { allowed(abash) }.isTrue()
                get { allowed("asdhahfasj".word) }.isFalse()
            }
        }
    }
}