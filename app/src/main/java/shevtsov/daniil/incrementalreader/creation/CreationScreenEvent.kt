package shevtsov.daniil.incrementalreader.creation

sealed class CreationScreenEvent {

    data class ShowItemSaved(val itemName: String) : CreationScreenEvent()

}