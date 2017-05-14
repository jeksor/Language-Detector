package com.esorokin.lantector.di.module

import com.esorokin.lantector.R
import com.esorokin.lantector.app.StringProvider
import com.esorokin.lantector.model.network.api.DefaultNetworkErrorAdapter
import com.esorokin.lantector.model.network.api.DefaultResponseAdapter
import com.esorokin.lantector.model.network.api.WatsonPlatformApi
import com.esorokin.lantector.model.network.api.handler.RxCallAdapterFactory
import com.esorokin.lantector.model.network.data.BaseResponse
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {
    companion object {
        private const val BASE_API_QUALIFIER = "BASE_API_QUALIFIER"
    }

    @Provides
    @Named(BASE_API_QUALIFIER)
    @Singleton
    fun provideBaseApiUrl(stringProvider: StringProvider): String {
        return stringProvider.getString(R.string.base_api_url)
    }

    @Provides
    @Named(BASE_API_QUALIFIER)
    @Singleton
    fun provideBaseRetrofit(
            @Named(BASE_API_QUALIFIER) baseApiUrl: String,
            retrofitBuilder: Retrofit.Builder,
            errorAdapter: DefaultNetworkErrorAdapter,
            responseAdapter: DefaultResponseAdapter): Retrofit {
        return retrofitBuilder
                .baseUrl(baseApiUrl)
                .addCallAdapterFactory(RxCallAdapterFactory.create(BaseResponse::class.java, errorAdapter, responseAdapter))
                .build()
    }

    @Provides
    @Singleton
    fun provideWatsonApi(@Named(BASE_API_QUALIFIER) retrofit: Retrofit): WatsonPlatformApi {
        return retrofit.create(WatsonPlatformApi::class.java)
    }
}
