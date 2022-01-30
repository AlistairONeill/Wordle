package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.game.RealGame
import uk.co.alistaironeill.wordle.domain.game.withAllowedWords
import uk.co.alistaironeill.wordle.domain.language.Dictionary
import java.io.File

fun main() {
    println("Loading...")

    val controller = load()

    println("Welcome to Wordle!")

    try {
        do {
            println("Please make your guess")
        } while (controller.accept(readln())?.also(::println) != null)
    } catch (e: Exception) {
        println("Congratulations!")
    }
}

private fun load(): GameController =
    File("data")
        .let(Dictionary::fromFolder)
        .run {
            getRandomSolution()
                .let(::RealGame)
                .withAllowedWords(this)
        }.let(::GameController)