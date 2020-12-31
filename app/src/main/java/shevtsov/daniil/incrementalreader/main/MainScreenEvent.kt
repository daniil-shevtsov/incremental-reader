package shevtsov.daniil.incrementalreader.main

sealed class MainScreenEvent {

    object OpenCreation : MainScreenEvent()

    object OpenLearning : MainScreenEvent()

    object OpenStructure : MainScreenEvent()

}