package shevtsov.daniil.incrementalreader.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import shevtsov.daniil.incrementalreader.core.util.toImmutable
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _events = MutableSharedFlow<MainScreenEvent>()
    val events = _events.toImmutable()

    fun onOpenCreation() {
        viewModelScope.launch {
            _events.emit(MainScreenEvent.OpenCreation)
        }
    }

    fun onOpenLearning() {
        viewModelScope.launch {
            _events.emit(MainScreenEvent.OpenLearning)
        }
    }

    fun onOpenStructure() {
        viewModelScope.launch {
            _events.emit(MainScreenEvent.OpenStructure)
        }
    }

}
