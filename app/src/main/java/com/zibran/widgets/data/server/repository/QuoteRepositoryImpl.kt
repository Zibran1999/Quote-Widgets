package com.zibran.widgets.data.server.repository

import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.model.response.Quote
import retrofit2.Response

class QuoteRepositoryImpl : QuoteRepository {
    override suspend fun getRandomQuote(): Response<Quote> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategorizedQuote(category: Category): Response<Quote> {
        TODO("Not yet implemented")
    }
}