package shevtsov.daniil.incrementalreader.structure.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.storage.domain.SaveCreatedUseCase
import shevtsov.daniil.incrementalreader.structure.domain.GetInformationItemsUseCase
import javax.inject.Inject

class StructureViewModel @Inject constructor(
    private val saveCreated: SaveCreatedUseCase,
    private val getInformationItems: GetInformationItemsUseCase,
    private val structureInformationItemMapper: StructureInformationItemMapper,
) : ViewModel() {

    private val _state = MutableStateFlow<StructureViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<StructureScreenEvent>()
    val events = _events.toImmutable()

    init {
        viewModelScope.launch {
            saveCreated(itemName = "lol1", text = "kek1")
            saveCreated(itemName = "lol2", text = "kek2")
            saveCreated(itemName = "lol3", text = "kek3")

            loadItems()
        }

    }

    private fun createInitialState(): StructureViewState {
        return StructureViewState(contentViewState = StructureContentViewState.Loading)
    }

    private fun loadItems() {
        viewModelScope.launch {
            val items = getInformationItems()
                .map { list -> list.map(structureInformationItemMapper::map) }
                .single()

            val newState =
                StructureViewState(contentViewState = StructureContentViewState.Content(items = items))
            _state.emit(newState)
        }

    }

    fun onItemSelected(id: Long) {
        val event = StructureScreenEvent.OpenLearning(itemId = id)
        viewModelScope.launch { _events.emit(value = event) }
    }

}