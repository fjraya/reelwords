package services

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import domain.Score
import infrastructure.ScoreRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ScoreServiceTest {
    @ParameterizedTest
    @MethodSource("wordsInput")
    fun whenUpdateScoreCalledCorrectResult(word: String, expected: Int) {
        val scoreRepositoryStub = mock<ScoreRepository> {
            on {getScore()} doReturn Score(hashMapOf('a' to 1, 'f' to 2, 'p' to 2))
        }
        val sut = ScoreService(scoreRepositoryStub)
        val actual = sut.updateScore(word)
        Assertions.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun wordsInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("af", 3),
                Arguments.arguments("affa", 6),
                Arguments.arguments("", 0),
                Arguments.arguments("zlzl", 0),

            )
        }
    }

}