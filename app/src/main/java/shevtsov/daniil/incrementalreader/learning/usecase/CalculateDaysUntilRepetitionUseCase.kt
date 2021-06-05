package shevtsov.daniil.incrementalreader.learning.usecase

import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue
import javax.inject.Inject

class CalculateDaysUntilRepetitionUseCase @Inject constructor() {

    operator fun invoke(scores: List<ScoreValue>): Double {
        val correctAnswerStreak = findAnswerStreak(scores)
        return 0.0
    }

    fun findAnswerStreak(scores: List<ScoreValue>): Int {
        return scores.formGroups { score -> score.value >= MinimumCorrect }
            .maxByOrNull { group -> group.count() }
            ?.count() ?: 0
    }

    fun <T> List<T>.formGroups(
        predicate: (value: T) -> Boolean
    ): List<List<T>> {
        val groups = mutableListOf<MutableList<T>>()
        forEach { value ->
            val lastGroup = groups.findLastGroup(predicate) as MutableList<T>?

            val lastValue = lastGroup
                ?.lastOrNull { groupValue -> predicate.invoke(groupValue) }

            if (predicate.invoke(value)) {
                if (lastValue != null && predicate.invoke(lastValue)) {
                    lastGroup?.add(value)
                } else {
                    groups.add(mutableListOf(value))
                }

            }
        }
        return groups
    }

    private companion object {
        object SpacedRepetition {
            val a = 6.0
            val b = -0.8
            val c = 0.28
            val d = 0.02
            val omega = 0.2
        }

        val MinimumCorrect = 3
    }

}

fun <T> List<List<T>>.findLastGroup(predicate: (value: T) -> Boolean) =
    lastOrNull { group -> group.any { groupValue -> predicate.invoke(groupValue) } }