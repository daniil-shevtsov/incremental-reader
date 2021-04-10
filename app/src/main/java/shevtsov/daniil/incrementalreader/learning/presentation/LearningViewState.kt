package shevtsov.daniil.incrementalreader.learning.presentation

sealed class LearningViewState {

    data class QuestionOnly(
        val itemName: String,
    ) : LearningViewState()

    data class AnswerShown(
        val itemName: String,
        val itemContent: String,
        val scoreList: List<ScoreItem>,
    ) : LearningViewState()

}
