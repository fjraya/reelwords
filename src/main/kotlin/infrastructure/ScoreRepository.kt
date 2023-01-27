package infrastructure

import domain.Score

class ScoreRepository(val name:String="scores.txt") {
    fun getScore(): Score {
        val score = Score()
        val fileContent = Thread.currentThread().contextClassLoader.getResourceAsStream(name)?.bufferedReader()
        var scoreLine = fileContent?.readLine()
        while (scoreLine != null) {
            val line = scoreLine.lowercase().split(" ")
            score.letters[line[0].single()] = line[1].toInt()
            scoreLine = fileContent?.readLine()
        }
        fileContent?.close()
        return score
    }
}