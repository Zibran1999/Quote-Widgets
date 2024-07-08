package com.zibran.widgets.di

import android.content.Context
import com.zibran.widgets.data.local.QuoteDatabase
import com.zibran.widgets.data.local.dao.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun getDatabaseInstance(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getDBInstance(context)
    }


    @Provides
    @Singleton
    fun getQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao {
        return quoteDatabase.getQuoteDao()
    }
}