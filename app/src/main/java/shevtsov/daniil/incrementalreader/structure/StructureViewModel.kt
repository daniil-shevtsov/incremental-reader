package shevtsov.daniil.incrementalreader.structure

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import shevtsov.daniil.incrementalreader.structure.domain.GetInformationItemsUseCase
import javax.inject.Inject

class StructureViewModel @Inject constructor(
    private val getInformationItems: GetInformationItemsUseCase,
    private val structureInformationItemMapper: StructureInformationItemMapper,
) : ViewModel() {

    private val _state = MutableStateFlow<StructureViewState>(value = createInitialState())
    val state = _state.toImmutable()

    private val _events = MutableSharedFlow<StructureScreenEvent>()
    val events = _events.toImmutable()

    init {

    }

    private fun createInitialState(): StructureViewState {
        val items = getInformationItems
            .invoke()
            .map(structureInformationItemMapper::map)

        return StructureViewState(items = items)
    }


}