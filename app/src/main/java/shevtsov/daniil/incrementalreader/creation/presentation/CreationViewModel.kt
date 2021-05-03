package shevtsov.daniil.incrementalreader.creation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.creation.navigation.CreationInitArguments
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.SaveOrUpdateItemUseCase
import javax.inject.Inject

class CreationViewModel @Inject constructor(
    private val saveOrUpdateItem: SaveOrUpdateItemUseCase,
    private val getSavedItem: GetSavedItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CreationViewState>(value = createInitialState())

    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<CreationScreenEvent>(replay = 0)
    val events = _events.asSharedFlow()

    private var currentName: String = ""
    private var currentText: String = ""

    private var currentId: Long? = null

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
            saveContent(
                id = currentId,
                name = currentName,
                content = currentText,
            )
            _events.emit(CreationScreenEvent.ShowItemSaved(itemName = currentName))
        }
    }

    private fun fillItemData(itemId: Long) {
        currentId = itemId

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

    fun onCreateChunk(selectedText: String) {
        viewModelScope.launch {
            saveContent(
                id = null,
                name = "$currentName: ${selectedText.substringBefore(" ")}",
                content = selectedText,
                parentId = currentId
            )
            _events.emit(CreationScreenEvent.ShowChunkCreated)
        }
    }

    fun onCreateCloze(selectedText: String) {
        viewModelScope.launch {
            saveContent(
                id = null,
                name = currentText.replaceFirst(selectedText, "_".repeat(selectedText.length)),
                content = currentText,
                parentId = currentId
            )
            _events.emit(CreationScreenEvent.ShowClozeCreated)
        }
    }

    private suspend fun saveContent(
        id: Long?,
        name: String,
        content: String,
        parentId: Long? = null
    ) {
        saveOrUpdateItem(id = id, name = name, content = content, parentId = parentId)
    }

}
