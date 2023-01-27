package domain

class Reels(val reels: MutableList<Reel> = arrayListOf()) {

    fun setRandomReels() {
        reels.forEach {
            it.setRandom()
        }
    }

    fun append(reel: Reel) {
        if (reels.isNotEmpty())  {
            if (reels.first().size() != reel.size()) throw InvalidReelSizeException("All the reels must have same length")
        }
        reels.add(reel)
    }

    fun current() = reels.map { it.current() }.joinToString(" ")

    fun rotate(word: String) {
        val wordSplit = mutableListOf<String>()
        word.lowercase().toCharArray().forEach { wordSplit.add(it.toString()) }
        if (!current().split(" ").containsAll(wordSplit)) throw InvalidWordRequestException("You don't have valid letters to form this word")

        wordSplit.forEach {
            for (reel in reels) {
                if (reel.current() == it.single()) {
                    reel.rotate()
                    break
                }
            }
        }
    }


    fun size() = reels.size

}