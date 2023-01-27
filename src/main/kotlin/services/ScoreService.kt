package services

import infrastructure.ScoreRepository

class ScoreService(private val scoreRepository: ScoreRepository = ScoreRepository()) {
    private var totalScore: Int = 0
    private val score = scoreRepository.getScore()

    fun updateScore(word: String): Int {
        val wordScore = score.score(word)
        totalScore += wordScore
        return wordScore
    }

    fun score() = totalScore
}