package shevtsov.daniil.incrementalreader.creation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.storage.domain.SaveCreatedUseCase
import javax.inject.Inject

class CreationViewModel @Inject constructor(
    private val saveCreated: SaveCreatedUseCase
) : ViewModel() {

    private val _events = MutableSharedFlow<CreationScreenEvent>()
    val events = _events.toImmutable()

    private var currentName: String = ""
    private var currentText: String = ""

    fun onNameEntered(text: String) {
        currentName = text
    }

    fun onContentEntered(text: String) {
        currentText = text
    }

    fun onSaveContent() {
        saveCreated(itemName = currentName, text = currentText)
        viewModelScope.launch {
            _events.emit(CreationScreenEvent.ShowItemSaved(itemName = currentName))
        }
    }

}
