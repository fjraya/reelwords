package domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReelTest {

    @Test
    fun whenCalledToRotateCorrectRotation() {
        val reel = Reel("a w s")
        reel.rotate()
        reel.rotate()
        Assertions.assertEquals('s', reel.current())
        reel.rotate()
        Assertions.assertEquals('a', reel.current())
    }

    @Test
    fun whenConstructAReelWithInvalidFormatThrows() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Reel("aa w s")
        }
    }

}