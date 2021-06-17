package shevtsov.daniil.incrementalreader.learning.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.learning.domain.AddRepetitionUseCase
import shevtsov.daniil.incrementalreader.learning.domain.GetScoreListUseCase
import shevtsov.daniil.incrementalreader.learning.domain.Score
import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments
import shevtsov.daniil.incrementalreader.learning.usecase.GetCalculatedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class LearningViewModel @Inject constructor(
    private val getScoreList: GetScoreListUseCase,
    private val getCalculatedItem: GetCalculatedItemUseCase,
    private val getSavedItem: GetSavedItemUseCase,
    private val addRepetition: AddRepetitionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<LearningViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<LearningScreenEvent>()
    val events = _events.toImmutable()

    // Remove side effects
    private val scoreList = getScoreList()
    private var selectedItem: InformationItem? = null

    fun onAction(action: LearningViewAction) {
        when (action) {
            is LearningViewAction.ProvideArguments -> onArguments(action.arguments)
            is LearningViewAction.ShowAnswer -> showAnswer()
            is LearningViewAction.SelectScore -> selectedScore(action.scoreId)
        }
    }

    private fun onArguments(arguments: LearningInitArguments) {
        when (arguments) {
            is LearningInitArguments.Empty -> handleEmpty()
            is LearningInitArguments.SelectedItem -> handleSelectedItem(id = arguments.itemId)
        }
    }

    private fun showAnswer() {
        val item = selectedItem

        if (item != null) {
            viewModelScope.launch {
                showFull(item)
            }
        }
    }

    private fun handleEmpty() {
        viewModelScope.launch {
            val item = getCalculatedItem.invoke()
            if(item != null) {
                selectedItem = item
                showQuestion(item)
            }
        }
    }

    private fun handleSelectedItem(id: Long) {
        viewModelScope.launch {
            val item = getSavedItem.invoke(itemId = id)
            selectedItem = item
            showQuestion(item)
        }
    }

    private fun selectedScore(scoreId: Long) {
        val item = selectedItem
        val score = scoreList.find { it.id == scoreId }

        if (item != null && score != null) {
            viewModelScope.launch {
                addRepetition(item = item, score = score)
            }
        }

    }

    private suspend fun showQuestion(item: InformationItem?) {
        if (item != null) {
            val state = LearningViewState.QuestionOnly(
                itemName = item.title
            )
            _state.emit(value = state)
        }
    }

    private suspend fun showFull(item: InformationItem?) {
        if (item != null) {
            val state = LearningViewState.AnswerShown(
                itemName = item.title,
                itemContent = item.content,
                scoreList = scoreList.map { it.toItem() }
            )
            _state.emit(value = state)
        }
    }

    private fun createInitialState(): LearningViewState {
        return LearningViewState.QuestionOnly(
            itemName = "",
        )
    }

    private fun Score.toItem() = ScoreItem(
        id = id,
        value = value.toString()
    )

}