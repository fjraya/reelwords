package domain

class Reel(input: String) {
    val reel: Array<Char>
    var index = 0

    init {
        reel = input.split(" ").map { it.lowercase().single() }.toTypedArray()
    }

    fun current() = reel[index]

    fun rotate() {
        index = (index + 1) % reel.size
    }

    fun size() = reel.size
    fun setRandom() {
        index = (0..reel.size).random() % reel.size
    }
}