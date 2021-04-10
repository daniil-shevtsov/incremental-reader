package shevtsov.daniil.incrementalreader.learning.domain

import android.util.Log
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class AddRepetitionUseCase @Inject constructor() {

    operator fun invoke(
        item: InformationItem,
        score: Score
    ) {
        Log.d(
            "AddRepetitionUseCase",
            "add repetition for item: ${item.title} with score: ${score.value}"
        )
    }

}