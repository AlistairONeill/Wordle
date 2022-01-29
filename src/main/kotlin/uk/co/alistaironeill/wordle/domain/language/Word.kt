package uk.co.alistaironeill.wordle.domain.language

data class Word(val letters: List<Letter>) {
    operator fun get(index: Int) = letters[index]
}

val String.word get() = uppercase()
    .map(Char::toString)
    .map(Letter::valueOf)
    .let(::Word)