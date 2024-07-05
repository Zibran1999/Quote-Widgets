package com.zibran.widgets.data.server.repository

import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.model.response.Quote
import retrofit2.Response

interface QuoteRepository {

    suspend fun getRandomQuote(): Response<Quote>

    suspend fun getCategorizedQuote(category: Category): Response<Quote>
}