package com.zibran.widgets.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zibran.widgets.data.local.entity.Quote

@Dao
interface QuoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote): Long


    @Query("Select * from quotes")
    suspend fun getQuotes(): List<Quote>
}