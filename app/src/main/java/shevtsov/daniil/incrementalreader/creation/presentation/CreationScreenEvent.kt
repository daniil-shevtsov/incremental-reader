package shevtsov.daniil.incrementalreader.creation.presentation

sealed class CreationScreenEvent {

    data class ShowItemSaved(val itemName: String) : CreationScreenEvent()

    object ShowChunkCreated : CreationScreenEvent()

    object ShowClozeCreated : CreationScreenEvent()

}