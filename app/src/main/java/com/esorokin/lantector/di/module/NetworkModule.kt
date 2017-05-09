package com.esorokin.lantector.di.module

import com.esorokin.lantector.utils.BuildUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private val CONNECT_TIMEOUT = 15
        private val READ_TIMEOUT = 30
        private val WRITE_TIMEOUT = 30
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildUtils.isTurnLogs) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        return SimpleXmlConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit.Builder {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
    }
}
