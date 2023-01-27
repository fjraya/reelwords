package domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class TrieTest {
    val trie = Trie()

    @ParameterizedTest
    @MethodSource("wordsInput")
    fun whenInsertAWordSearchReturnsCorrectResult(word: String, search: String, expected: Boolean) {
        trie.insert(word)
        Assertions.assertEquals(expected, trie.search(search))
    }


    @Test
    fun whenInsertTwiceTheSameWordIdempotency() {
        trie.insert("hello")
        trie.insert("hello")
        Assertions.assertEquals(true, trie.search("hello"))
        trie.delete("hello")
        Assertions.assertEquals(false, trie.search("hello"))
    }


    @Test
    fun whenInsertMultipleWordsReturnCorrectResult() {
        trie.insert("cats")
        trie.insert("cat")
        trie.insert("any")
        trie.insert("anyone")
        trie.insert("both")
        trie.insert("boring")
        Assertions.assertEquals(true, trie.search("cat"))
        Assertions.assertEquals(true, trie.search("cats"))
        Assertions.assertEquals(true, trie.search("any"))
        Assertions.assertEquals(true, trie.search("anyone"))
        Assertions.assertEquals(true, trie.search("both"))
        Assertions.assertEquals(true, trie.search("boring"))
        Assertions.assertEquals(false, trie.search("anyones"))
        Assertions.assertEquals(false, trie.search("cati"))
        Assertions.assertEquals(false, trie.search("let"))
    }

    @Test
    fun whenInsertMultipleWordsAndDeleteReturnCorrectResult() {
        trie.insert("parallel")
        trie.insert("parallels")
        Assertions.assertEquals(true, trie.search("parallels"))
        trie.delete("parallels")
        Assertions.assertEquals(false, trie.search("parallels"))
        Assertions.assertEquals(true, trie.search("parallel"))
    }




    @Test
    fun whenInsertAnotherMultipleWordsAndDeleteReturnCorrectResult() {
        trie.insert("parallel")
        trie.insert("any")
        Assertions.assertEquals(true, trie.search("parallel"))
        trie.delete("parallel")
        Assertions.assertEquals(false, trie.search("parallel"))
    }

    @Test
    fun whenInsertOverlappingWordsAndDeleteReturnCCorrectResult() {
        trie.insert("any")
        trie.insert("anywhere")
        trie.insert("anyone")
        trie.insert("anyones")
        Assertions.assertEquals(true, trie.search("any"))
        Assertions.assertEquals(true, trie.search("anywhere"))
        Assertions.assertEquals(true, trie.search("anyone"))
        Assertions.assertEquals(true, trie.search("anyones"))
        trie.delete("anywhere")
        Assertions.assertEquals(true, trie.search("any"))
        Assertions.assertEquals(false, trie.search("anywhere"))
        Assertions.assertEquals(true, trie.search("anyone"))
        Assertions.assertEquals(true, trie.search("anyones"))
        trie.delete("any")
        Assertions.assertEquals(false, trie.search("any"))
        Assertions.assertEquals(false, trie.search("anywhere"))
        Assertions.assertEquals(true, trie.search("anyone"))
        Assertions.assertEquals(true, trie.search("anyones"))
        trie.delete("anyones")
        Assertions.assertEquals(false, trie.search("any"))
        Assertions.assertEquals(false, trie.search("anywhere"))
        Assertions.assertEquals(true, trie.search("anyone"))
        Assertions.assertEquals(false, trie.search("anyones"))
        trie.delete("anyone")
        Assertions.assertEquals(false, trie.search("any"))
        Assertions.assertEquals(false, trie.search("anywhere"))
        Assertions.assertEquals(false, trie.search("anyone"))
        Assertions.assertEquals(false, trie.search("anyones"))
    }


    companion object {
        @JvmStatic
        fun wordsInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("parallel", "parallel", true),
                Arguments.arguments("", "", true),
                Arguments.arguments("", "hola", false),
                Arguments.arguments("cats", "cat", false),
                Arguments.arguments("cat", "cats", false),
            )
        }
    }


}