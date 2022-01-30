package uk.co.alistaironeill.wordle.domain.game

val randomResult get() = List(5) { ResultValue.values().random() }.let(::Result)