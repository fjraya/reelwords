package infrastructure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ScoreRepositoryIntegrationTest {
    @Test
    fun whenCalledFromResourceCorrectIngestion() {
        val scoreRepository = ScoreRepository()
        val score = scoreRepository.getScore()
        Assertions.assertEquals(26, score.size())
    }
}