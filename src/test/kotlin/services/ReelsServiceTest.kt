package services

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import domain.InvalidWordException
import domain.Reels
import domain.Trie
import infrastructure.ReelsRepository
import infrastructure.TrieRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReelsServiceTest {
    @Test
    fun whenCalledToRequestWordCorrectCallToSearch() {
        val trieMock = mock<Trie> {
            on { search("a word") } doReturn true
        }
        val sut = configureSut(trieMock)
        val word = "a word"
        sut.requestWord(word)
        verify(trieMock, times(1)).search(word)
    }


    @Test
    fun whenCalledToRequestWithInvalidWordThrow() {
        val trieStub = mock<Trie> {
            on { search("a word") } doReturn false
        }
        val sut = configureSut(trieStub, null)
        Assertions.assertThrows(InvalidWordException::class.java) {
            sut.requestWord("a word")
        }
    }

    @Test
    fun whenCalledToRequestWithValidWordCorrectCallToRotate() {
        val trieMock = mock<Trie> {
            on { search("ahb") } doReturn true
        }
        val reelsStub = mock<Reels> {
            on { current() } doReturn "a g h t"

        }
        val sut = configureSut(trieMock, reelsStub)
        sut.requestWord("ahb")
        verify(reelsStub, times(1)).rotate("ahb")
    }


    private fun configureSut(trieMock: Trie, reelsStub: Reels?=null): ReelsService {
        val innerReelsStub = reelsStub
            ?: mock {
                on { current() } doReturn "a g h t"

            }
        val trieRepositoryStub = mock<TrieRepository> {
            on { getTrie() } doReturn trieMock
        }

        val reelsRepositoryStub = mock<ReelsRepository> {
            on { getReels() } doReturn innerReelsStub
        }
        return ReelsService(reelsRepositoryStub, trieRepositoryStub)
    }
}