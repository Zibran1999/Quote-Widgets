package com.zibran.widgets.data.server.repository

import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.model.response.Quote
import com.zibran.widgets.utils.mapper.ApiResult
import retrofit2.Response

interface QuoteRepository {

    suspend fun getRandomQuote(): ApiResult<Quote>

    suspend fun getCategorizedQuote(category: Category): ApiResult<Quote>
}