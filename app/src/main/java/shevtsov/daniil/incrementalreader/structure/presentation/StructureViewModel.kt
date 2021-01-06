package shevtsov.daniil.incrementalreader.structure.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
        saveCreated(itemName = "lol1", text = "kek1")
        saveCreated(itemName = "lol2", text = "kek2")
        saveCreated(itemName = "lol3", text = "kek3")

        viewModelScope.launch {
            _state.emit(value = createInitialState())
        }

    }

    private fun createInitialState(): StructureViewState {
        val items = getInformationItems
            .invoke()
            .map(structureInformationItemMapper::map)

        return StructureViewState(items = items)
    }

    fun onItemSelected(id: String) {
        val event = StructureScreenEvent.OpenLearning(itemId = id)
        viewModelScope.launch { _events.emit(value = event) }
    }

}