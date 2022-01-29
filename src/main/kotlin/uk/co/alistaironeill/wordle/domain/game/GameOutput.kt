package uk.co.alistaironeill.wordle.domain.game

sealed interface GameOutput {
    object InvalidInput : GameOutput

    object Victory : GameOutput

    data class Result(val values: List<ResultValue>) : GameOutput {
        operator fun get(index: Int) = values[index]
    }

    enum class ResultValue {
        GREY,
        YELLOW,
        GREEN
    }
}