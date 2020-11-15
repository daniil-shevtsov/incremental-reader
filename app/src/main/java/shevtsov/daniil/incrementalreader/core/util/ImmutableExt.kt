package shevtsov.daniil.incrementalreader.core.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toImmutable() = this as LiveData<T>
