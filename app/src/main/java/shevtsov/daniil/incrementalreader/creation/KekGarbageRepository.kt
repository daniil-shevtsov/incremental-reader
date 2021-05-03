package shevtsov.daniil.incrementalreader.creation

import android.content.Context
import shevtsov.daniil.incrementalreader.R
import java.io.InputStream
import javax.inject.Inject

class KekGarbageRepository @Inject constructor(
    private val context: Context
) {

    fun readPeace(): InputStream {
        return context.resources.openRawResource(R.raw.peace)
    }

}