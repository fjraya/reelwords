package infrastructure

import domain.Reel
import domain.Reels

class ReelsRepository(val name:String = "reels.txt") {
    fun getReels(): Reels {
        val reels = Reels()
        val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream(name)?.bufferedReader()
        var reel = fileContent?.readLine()
        while (reel != null) {
            reels.append(Reel(reel))
            reel = fileContent?.readLine()
        }
        fileContent?.close()
        return reels
    }
}