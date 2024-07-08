package com.zibran.widgets.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zibran.widgets.data.local.dao.QuoteDao
import com.zibran.widgets.data.local.entity.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = true)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao

    companion object {

        fun getDBInstance(context: Context): QuoteDatabase {
            return Room.databaseBuilder(
                context, QuoteDatabase::class.java, "quote_db"
            ).build()
        }
    }
}