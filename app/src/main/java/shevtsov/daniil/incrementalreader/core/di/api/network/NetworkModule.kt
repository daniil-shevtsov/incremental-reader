package shevtsov.daniil.incrementalreader.core.di.api.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shevtsov.daniil.incrementalreader.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
open class NetworkModule {

    companion object {
        private const val BASE_URL = "https://base.url/"
        const val BASE_URL_NAME = "BASE_URL"

        private const val TIME_OUT_SECONDS = 10L
    }

    @Provides
    @Named(BASE_URL_NAME)
    open fun provideUrl() = BASE_URL

    @Provides
    fun provideRetrofit(
        @Named(BASE_URL_NAME) endpointUrl: String,
        converterFactory: Converter.Factory,
        callAdapter: CallAdapter.Factory,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapter)
        .client(okHttpClient)
        .baseUrl(endpointUrl)
        .build()

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .apply {
            addInterceptor(loggingInterceptor)

            connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
        }
        .build()

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideGson() = GsonBuilder().create()

    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

}
