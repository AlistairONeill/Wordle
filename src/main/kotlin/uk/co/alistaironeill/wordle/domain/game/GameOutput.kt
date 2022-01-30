package uk.co.alistaironeill.wordle.domain.game


data class Result(val values: List<ResultValue>) {
    operator fun get(index: Int) = values[index]
}

enum class ResultValue {
    GREY,
    YELLOW,
    GREEN
}
