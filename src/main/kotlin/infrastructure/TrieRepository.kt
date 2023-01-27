package infrastructure

import domain.Trie

class TrieRepository(val name: String="american-english-large.txt") {
    fun getTrie(): Trie {
        val trie = Trie()
        val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream(name)?.bufferedReader()
        var word = fileContent?.readLine()
        while (word != null) {
            if (word.chars().allMatch(Character::isLetter)) trie.insert(word)
            word = fileContent?.readLine()
        }
        fileContent?.close()
        return trie
    }
}