package uk.co.alistaironeill.wordle.playable

import uk.co.alistaironeill.wordle.domain.game.RealGame
import uk.co.alistaironeill.wordle.domain.language.RealDictionary
import java.io.File

fun main() {
    println("Loading...")

    val controller = load()

    println("Welcome to Wordle!")

    try {
        while (true) {
            println("Please make your guess")
            val input = readln()
            val output = controller.accept(input)
            println(output)
        }
    } catch (e: Exception) {
        println("Congratulations!")
    }
}

private fun load(): GameController {
    val dictionary = RealDictionary.fromFolder(File("data"))
    val game = RealGame(dictionary, dictionary.getRandomSolution())
    return GameController(game)
}