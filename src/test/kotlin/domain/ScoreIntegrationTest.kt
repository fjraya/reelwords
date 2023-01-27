package domain

import infrastructure.ScoreRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ScoreIntegrationTest {
    @ParameterizedTest
    @MethodSource("wordsInput")
    fun whenCalledWithWordReturnCorrectScore(word: String, expected: Int) {
        val scoreRepository = ScoreRepository()
        val score = scoreRepository.getScore()
        val actual = score.score(word)
        Assertions.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun wordsInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("anyone", 9),
                Arguments.arguments("rake", 8)
            )
        }
    }

}