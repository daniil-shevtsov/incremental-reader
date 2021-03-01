package shevtsov.daniil.incrementalreader.creation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.creation.navigation.CreationInitArguments
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.SaveCreatedUseCase
import javax.inject.Inject

class CreationViewModel @Inject constructor(
    private val saveCreated: SaveCreatedUseCase,
    private val getSavedItem: GetSavedItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CreationViewState>(value = createInitialState())

    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<CreationScreenEvent>()
    val events = _events.toImmutable()

    private var currentName: String = ""
    private var currentText: String = ""

    fun onArguments(arguments: CreationInitArguments) {
        when (arguments) {
            is CreationInitArguments.Create -> Unit
            is CreationInitArguments.Edit -> fillItemData(itemId = arguments.itemId)
        }
    }

    fun onNameEntered(text: String) {
        currentName = text
    }

    fun onContentEntered(text: String) {
        currentText = text
    }

    fun onSaveContent() {
        viewModelScope.launch {
            saveCreated(itemName = currentName, text = currentText)
            _events.emit(CreationScreenEvent.ShowItemSaved(itemName = currentName))
        }
    }

    private fun fillItemData(itemId: Long) {
        viewModelScope.launch {
            val item = getSavedItem(itemId)
            if (item != null) {
                currentName = item.title
                currentText = item.content
                _state.emit(CreationViewState(title = item.title, content = item.content))
            }
        }
    }

    private fun createInitialState() = CreationViewState(
        title = "",
        content = "",
    )

}
