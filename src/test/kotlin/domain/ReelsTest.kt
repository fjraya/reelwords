package domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class ReelsTest {
    private lateinit var reels: Reels
    @BeforeEach
    fun setUp() {
        reels = Reels(mutableListOf(Reel("a b c"), Reel("a d f"), Reel("d u g")))
    }

    @Test
    fun whenAppendAReelWithDifferentSizeThrows() {
        Assertions.assertThrows(InvalidReelSizeException::class.java) {
            reels.append(Reel("a w e r"))
        }
    }

    @Test
    fun whenAppendAReelWithCorrectSizeReturnNewSize() {
        reels.append(Reel("a e r"))
        Assertions.assertEquals(4, reels.size())
    }

    @ParameterizedTest
    @MethodSource("wordsInput")
    fun whenRotateWithValidWordCorrectRotation(input: String, expected: String) {
        reels.rotate(input)
        val actual = reels.current()
        Assertions.assertEquals(expected, actual)
    }


    @Test
    fun whenRotateWithInvalidWordThrows() {
        Assertions.assertThrows(InvalidWordRequestException::class.java) {
            reels.rotate("ady")
        }
    }

    companion object {
        @JvmStatic
        fun wordsInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("ad", "b a u"),
                Arguments.arguments("daa", "b d u")
            )
        }
    }

}