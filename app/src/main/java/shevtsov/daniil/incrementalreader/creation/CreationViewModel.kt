package shevtsov.daniil.incrementalreader.creation

import androidx.lifecycle.ViewModel
import shevtsov.daniil.incrementalreader.creation.domain.SaveCreatedUseCase
import javax.inject.Inject

class CreationViewModel @Inject constructor(
    private val saveCreated: SaveCreatedUseCase
) : ViewModel() {

    private var currentName: String = ""
    private var currentText: String = ""

    fun onTextEntered(text: String) {
        currentText = text
    }

    fun onSaveText() {
        saveCreated(itemName = "lol", text = currentText)
    }

    fun saveTextName(nameText: String) {
        currentName = nameText
    }

}
