package shevtsov.daniil.incrementalreader.learning.presentation

import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments

sealed class LearningViewAction {

    data class ProvideArguments(
        val arguments: LearningInitArguments
    ) : LearningViewAction()

    object ShowAnswer : LearningViewAction()

    data class SelectScore(val scoreId: Long) : LearningViewAction()

}