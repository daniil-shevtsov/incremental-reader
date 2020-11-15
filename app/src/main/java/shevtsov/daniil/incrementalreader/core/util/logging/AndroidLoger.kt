package shevtsov.daniil.incrementalreader.core.util.logging

import android.util.Log
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {
    companion object {
        private const val STANDARD_TAG = "AndroidLogger"
    }

    override fun e(message: String, tag: String?) {
        Log.e(tag ?: STANDARD_TAG, message)
    }

    override fun d(message: String, tag: String?) {
        Log.d(tag ?: STANDARD_TAG, message)
    }
}
