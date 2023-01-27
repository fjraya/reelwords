package infrastructure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrieRepositoryIntegrationTest {
    @Test
    fun whenCalledFromResourceCorrectIngestion() {
        val trieRepository = TrieRepository()
        val trie = trieRepository.getTrie()
        Assertions.assertEquals(true, trie.search("a"))
    }
}