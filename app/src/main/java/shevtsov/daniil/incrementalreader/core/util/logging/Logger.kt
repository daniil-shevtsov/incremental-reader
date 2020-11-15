package shevtsov.daniil.incrementalreader.core.util.logging

interface Logger {
    fun e(message: String, tag: String? = null)
    fun d(message: String, tag: String? = null)
}
