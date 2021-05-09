package shevtsov.daniil.incrementalreader.main.presentation

sealed class MainScreenEvent {

    object OpenCreation : MainScreenEvent()

    object LoadPeace : MainScreenEvent()

    object OpenLearning : MainScreenEvent()

    object OpenStructure : MainScreenEvent()

}