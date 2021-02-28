package shevtsov.daniil.incrementalreader.learning.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.learning.navigation.LearningInitArguments
import shevtsov.daniil.incrementalreader.learning.usecase.GetCalculatedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class LearningViewModel @Inject constructor(
    private val getCalculatedItem: GetCalculatedItemUseCase,
    private val getSavedItem: GetSavedItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<LearningViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<LearningScreenEvent>()
    val events = _events.toImmutable()

    fun onArguments(arguments: LearningInitArguments) {
        when (arguments) {
            is LearningInitArguments.Empty -> handleEmpty()
            is LearningInitArguments.SelectedItem -> handleSelectedItem(id = arguments.itemId)
        }

    }

    private fun handleEmpty() {
        viewModelScope.launch {
            getCalculatedItem.invoke().collect { item ->
                showItem(item)
            }
        }
    }

    private fun handleSelectedItem(id: Long) {
        viewModelScope.launch {
            val item = getSavedItem.invoke(itemId = id)
            showItem(item)
        }
    }

    private suspend fun showItem(item: InformationItem?) {
        if (item != null) {
            val state = LearningViewState(
                itemName = item.title,
                itemContent = item.content
            )

            _state.emit(value = state)
        }
    }

    private fun createInitialState(): LearningViewState {
        return LearningViewState(
            itemName = "",
            itemContent = ""
        )
    }

}