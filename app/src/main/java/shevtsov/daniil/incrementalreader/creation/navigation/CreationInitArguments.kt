package shevtsov.daniil.incrementalreader.creation.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class CreationInitArguments : Parcelable {

    @Parcelize
    object Create : CreationInitArguments()

    @Parcelize
    data class Edit(val itemId: Long) : CreationInitArguments()

}