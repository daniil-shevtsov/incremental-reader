package shevtsov.daniil.incrementalreader.creation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.creation.KekGarbageRepository
import shevtsov.daniil.incrementalreader.creation.navigation.CreationInitArguments
import shevtsov.daniil.incrementalreader.storage.domain.GetSavedItemUseCase
import shevtsov.daniil.incrementalreader.storage.domain.SaveOrUpdateItemUseCase
import javax.inject.Inject

class CreationViewModel @Inject constructor(
    private val saveOrUpdateItem: SaveOrUpdateItemUseCase,
    private val getSavedItem: GetSavedItemUseCase,
    private val kekGarbageRepository: KekGarbageRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<CreationViewState>(value = createInitialState())

    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<CreationScreenEvent>(replay = 0)
    val events = _events.asSharedFlow()

    private var currentName: String = ""
    private var currentText = mutableMapOf<Long, String>()

    private var currentId: Long? = null

    fun onArguments(arguments: CreationInitArguments) {
        when (arguments) {
            is CreationInitArguments.Create -> loadEmpty()
            is CreationInitArguments.LoadPeace -> loadPeace()
            is CreationInitArguments.Edit -> fillItemData(itemId = arguments.itemId)
        }
    }

    fun onNameEntered(text: String) {
        currentName = text
    }

    fun onContentEntered(id: Long, text: String) {
        currentText[id] = text
    }

    fun onSaveContent() {
        viewModelScope.launch {
            saveContent(
                id = currentId,
                name = formTextName(),
                content = formText(),
            )
            _events.emit(CreationScreenEvent.ShowItemSaved(itemName = currentName))
        }
    }

    private fun loadEmpty() {
        viewModelScope.launch {
            val items = (0..10).toList().map { it.toLong() to "" }
            _state.emit(

                CreationViewState(
                    title = "",
                    contentItems = items
                )
            )
        }
    }

    private fun loadPeace() {
        viewModelScope.launch {
            val items = mutableListOf<String>()
            kekGarbageRepository.readPeace().bufferedReader().lineSequence().asFlow()
                .toList(items)
            _state.emit(
                CreationViewState(
                    title = "",
                    contentItems = items.mapIndexed { id, value -> id.toLong() to value }
                )
            )

        }
    }

    private fun fillItemData(itemId: Long) {
        currentId = itemId

        viewModelScope.launch {
            val item = getSavedItem(itemId)
            if (item != null) {
                currentName = item.title
                currentText = item.content.split('\n')
                    .mapIndexed { index, line -> index.toLong() to line }
                    .toMap()
                    .toMutableMap()
                _state.emit(CreationViewState(title = item.title, contentItems = currentText.toList()))
//                currentName = item.title
//                currentText = item.content
//                _state.emit(CreationViewState(title = item.title))
            }
        }
    }

    private fun createInitialState() = CreationViewState(
        title = "",
    )

    fun onCreateChunk(selectedText: String) {
        viewModelScope.launch {
            saveContent(
                id = null,
                name = formChunkName(selectedText),
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
                name = formClozeName(selectedText),
                content = formText(),
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

    private fun formTextName(): String {
        return currentName
    }

    private fun formChunkName(selectedText: String): String {
        return "$currentName: ${selectedText.substringBefore(" ")}"
    }

    private fun formClozeName(selectedText: String): String {
        return formText().replaceFirst(selectedText, "_".repeat(selectedText.length))
    }

    private fun formText(): String {
        return currentText.map { it.value }.joinToString("\n")
    }

}
