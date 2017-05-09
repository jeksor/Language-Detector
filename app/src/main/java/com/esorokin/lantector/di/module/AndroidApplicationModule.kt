package com.esorokin.lantector.di.module

import android.content.Context
import com.esorokin.lantector.app.StringProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidApplicationModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideStringProvider(): StringProvider {
        return object : StringProvider {
            override fun getString(stringRes: Int, vararg formatArgs: Any): String = context.getString(stringRes, formatArgs)
        }
    }
}
