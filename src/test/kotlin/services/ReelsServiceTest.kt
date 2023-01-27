package services

import com.nhaarman.mockitokotlin2.*
import domain.Reels
import domain.Trie
import infrastructure.ReelsRepository
import infrastructure.TrieRepository
import org.junit.jupiter.api.Test
import java.util.*

class ReelsServiceTest {
    @Test
    fun whenCalledToRequestWordCorrectCallToSearch() {
        val trieMock = mock<Trie> {
            on { search(any())} doReturn true
        }
        val reelsStub = mock<Reels> {}
        val trieRepositoryStub = mock<TrieRepository> {
            on { getTrie() } doReturn trieMock
        }
        val reelsRepositoryStub = mock<ReelsRepository> {
            on { getReels() } doReturn reelsStub
        }
        val sut = ReelsService(reelsRepositoryStub, trieRepositoryStub)
        val word = "a word"
        sut.requestWord(word)
        verify(trieMock, times(1)).search(word)
    }

    @Test
    fun whenCalledToRequestWithInvalidWordThrow {
        val trieMock = mock<Trie> {
            on { search(any())} doReturn false
        }

    }
}