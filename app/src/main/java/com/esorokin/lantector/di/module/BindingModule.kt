package com.esorokin.lantector.di.module

import com.esorokin.lantector.model.storage.Database
import com.esorokin.lantector.model.storage.PersistentDatabase
import com.esorokin.lantector.presentation.error.DefaultErrorProcessor
import com.esorokin.lantector.presentation.error.ErrorProcessor
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {
    @Binds
    abstract fun database(database: PersistentDatabase): Database

    @Binds
    abstract fun defaultErrorProcessor(errorProcessor: DefaultErrorProcessor): ErrorProcessor
}