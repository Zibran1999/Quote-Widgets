package com.zibran.widgets.di

import com.zibran.widgets.data.server.repository.QuoteRepository
import com.zibran.widgets.data.server.repository.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
fun interface RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideRepository(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteRepository
}