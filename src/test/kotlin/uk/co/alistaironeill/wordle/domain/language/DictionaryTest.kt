package uk.co.alistaironeill.wordle.domain.language

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.doesNotContain
import java.io.File

class DictionaryTest {
    @Nested
    inner class FromFolder {
        private val dictionary = Dictionary.fromFolder(File("data"))

        private val acorn = "acorn".word
        private val abash = "abash".word

        @Test
        fun `solutions populated correctly`() {
            expectThat(dictionary.solutions) {
                contains(acorn)
                doesNotContain(abash)
            }
        }

        @Test
        fun `all words populated correctly`() {
            expectThat(dictionary.allWords) {
                contains(acorn)
                contains(abash)
                doesNotContain("asjdashd")
            }
        }
    }
}