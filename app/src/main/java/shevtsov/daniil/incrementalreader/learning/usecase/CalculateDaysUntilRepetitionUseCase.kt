package shevtsov.daniil.incrementalreader.learning.usecase

import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.pow

class CalculateDaysUntilRepetitionUseCase @Inject constructor() {

    operator fun invoke(scores: List<ScoreValue>): Double {
        val correctAnswerStreak = findAnswerStreak(scores)


        return with(SpacedRepetition) {
            (max(minScore, assumedScore + scores.sumOf { score ->

                b + c * score.value + d * score.value * score.value
            }).pow(theta * correctAnswerStreak)) * a
        }
    }

    fun findAnswerStreak(scores: List<ScoreValue>): Int {
        return scores.formGroups { score -> score.value >= MinimumCorrect }
            .filter { group -> group.all { score -> score.value >= MinimumCorrect } }
            .maxByOrNull { group -> group.count() }
            ?.count() ?: 0
    }


    private companion object {
        object SpacedRepetition {
            val minScore = 1.3
            val assumedScore = 2.5

            val a = 6.0
            val b = -0.8
            val c = 0.28
            val d = 0.02
            val theta = 0.2
        }

        val MinimumCorrect = 3
    }

}

fun <T> List<List<T>>.findLastGroup(predicate: (value: T) -> Boolean) =
    lastOrNull { group -> group.any { groupValue -> predicate.invoke(groupValue) } }

fun <T> List<T>.formGroups(
    predicate: (value: T) -> Boolean
): List<List<T>> {
    if (isEmpty()) {
        return emptyList()
    }

    val groups = mutableListOf<MutableList<T>>()
    groups.add(mutableListOf(first()))

    var lastGroup: MutableList<T> = groups.first()
    var lastValue: T = lastGroup.first()

    drop(1).forEach { value ->
        if (predicate.invoke(lastValue) == predicate.invoke(value)) {
            lastGroup.add(value)
        } else {
            groups.add(mutableListOf(value))
            lastGroup = groups.last()
        }
        lastValue = value
//        val lastGroup = groups.findLastGroup(predicate) as MutableList<T>?
//
//        val lastValue = lastGroup?.lastOrNull(predicate)

//        val valueValid = predicate.invoke(value)
//        val lastValueValid = lastValue?.let { predicate.invoke(value) }
//
//        if (lastValueValid == null) {
//            groups.add(mutableListOf(value))
//        } else if (valueValid == lastValueValid) {
//            lastGroup?.add(value)
//        } else {
//            groups.add(mutableListOf(value))
//        }

//        if (predicate.invoke(value)) {
//            if (lastValue != null && predicate.invoke(lastValue)) {
//                lastGroup?.add(value)
//            } else {
//                groups.add(mutableListOf(value))
//            }
//        }
    }
    return groups
}