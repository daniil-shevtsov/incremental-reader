package shevtsov.daniil.incrementalreader.core.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

fun <T> MutableLiveData<T>.toImmutable() = this as LiveData<T>
fun <T> MutableSharedFlow<T>.toImmutable() = this as SharedFlow<T>
