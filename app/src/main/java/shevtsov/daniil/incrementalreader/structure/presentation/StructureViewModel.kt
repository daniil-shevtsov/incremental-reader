package shevtsov.daniil.incrementalreader.structure.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.structure.domain.GetInformationItemsUseCase
import javax.inject.Inject

class StructureViewModel @Inject constructor(
//    private val saveCreated: SaveCreatedUseCase,
    private val getInformationItems: GetInformationItemsUseCase,
    private val structureInformationItemMapper: StructureInformationItemMapper,
) : ViewModel() {

    private val _state = MutableStateFlow<StructureViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<StructureScreenEvent>()
    val events = _events.toImmutable()

    init {
        loadItems()
    }

    private fun createInitialState(): StructureViewState {
        return StructureViewState(contentViewState = StructureContentViewState.Loading)
    }

    private fun loadItems() {
        viewModelScope.launch {
            Log.d("KEK", "try to get items")
            val items = getInformationItems()
                .map { list -> list.map(structureInformationItemMapper::map) }
                .collect { items ->
                    Log.d("KEK", "got items: $items")
                    val newState =
                        StructureViewState(
                            contentViewState = StructureContentViewState.Content(
                                items = items
                            )
                        )
                    _state.emit(newState)
                }

        }

    }

    fun onItemSelected(id: Long) {
        val event = StructureScreenEvent.OpenLearning(itemId = id)
        viewModelScope.launch { _events.emit(value = event) }
    }

}