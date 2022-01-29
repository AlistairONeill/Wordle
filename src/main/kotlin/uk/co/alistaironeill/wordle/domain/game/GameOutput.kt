package uk.co.alistaironeill.wordle.domain.game

sealed interface GameOutput {
    object InvalidInput : GameOutput

    object Victory : GameOutput

    data class Result(val values: List<ResultValue>) : GameOutput

    enum class ResultValue(val symbol: Char) {
        GREY('.'),
        YELLOW('?'),
        GREEN('!')
    }
}