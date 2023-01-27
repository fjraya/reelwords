package domain

class Score(val letters: HashMap<Char, Int> = hashMapOf()) {
    fun size() = letters.size

    fun score(word: String): Int {
        var result = 0
        word.forEach { result += letters[it]?:0 }
        return result
    }
}