package shevtsov.daniil.incrementalreader.learning

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class LearningInitArguments : Parcelable {

    @Parcelize
    object Empty : LearningInitArguments()

    @Parcelize
    data class SelectedItem(val itemId: String) : LearningInitArguments()

}