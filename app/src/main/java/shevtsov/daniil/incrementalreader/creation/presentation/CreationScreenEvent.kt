package shevtsov.daniil.incrementalreader.creation.presentation

sealed class CreationScreenEvent {

    data class ShowItemSaved(val itemName: String) : CreationScreenEvent()

}