package shevtsov.daniil.incrementalreader.learning.usecase

import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue
import javax.inject.Inject

class CalculateDaysUntilRepetitionUseCase @Inject constructor() {

    operator fun invoke(scores: List<ScoreValue>): Double {
        return 0.0
    }

    private companion object {
        object SpacedRepetition {
            val a = 6.0
            val b = -0.8
            val c = 0.28
            val d = 0.02
            val omega = 0.2
        }
    }

}