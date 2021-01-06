package shevtsov.daniil.incrementalreader.learning.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import javax.inject.Inject

class LearningViewModel @Inject constructor(
    private val getSavedItem: GetSavedItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<LearningViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<LearningScreenEvent>()
    val events = _events.toImmutable()

    private fun createInitialState(): LearningViewState {
        return LearningViewState(
            itemName = "",
            itemContent = ""
        )
    }

}