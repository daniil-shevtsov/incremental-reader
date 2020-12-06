package shevtsov.daniil.incrementalreader.main

sealed class MainEvent {

    object OpenCreation : MainEvent()

    object OpenLearning : MainEvent()

    object OpenStructure : MainEvent()

}