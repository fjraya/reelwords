package services

import domain.InvalidWordException
import domain.Reels
import domain.Trie
import infrastructure.ReelsRepository
import infrastructure.TrieRepository

class ReelsService(private val reelsRepository: ReelsRepository = ReelsRepository(), private val trieRepository: TrieRepository = TrieRepository()) {
    private val trie: Trie = trieRepository.getTrie()
    private val reels: Reels = reelsRepository.getReels()
    fun init() {
        reels.setRandomReels()
    }

    fun requestWord(word: String) {
        if (!trie.search(word)) throw InvalidWordException("Invalid word: $word")
        reels.rotate(word)
    }

    fun current() = reels.current()

}