import services.ReelsService
import services.ScoreService

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val reelsService = ReelsService()
            val scoreService = ScoreService()

            println("REELWORDS")
            reelsService.init()
            while (true) {
                println("Score: ${scoreService.score()}")
                println("Reels: " + reelsService.current())
                var validWord = false
                var wordRequest: String? = null
                while (!validWord) {
                    print("Enter a word or '.' to exit: ")
                    wordRequest = readLine()
                    if (wordRequest != null) {
                        if (wordRequest == ".") return
                        validWord = true
                    }
                }
                try {
                    reelsService.requestWord(wordRequest!!)
                    val requestScore = scoreService.updateScore(wordRequest)
                    println("Well done! Score for $wordRequest: $requestScore")
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }
}