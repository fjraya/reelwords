package infrastructure

import infrastructure.ReelsRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReelsRepositoryIntegrationTest {
    @Test
    fun whenCalledFromResourceCorrectIngestion() {
        val reelsRepository = ReelsRepository()
        val reels = reelsRepository.getReels()
        Assertions.assertEquals("u e i a a a", reels.current())
    }


}