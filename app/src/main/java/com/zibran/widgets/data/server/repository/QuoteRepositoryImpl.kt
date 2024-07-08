package com.zibran.widgets.data.server.repository

import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.model.response.Quote
import com.zibran.widgets.data.server.service.QuoteService
import com.zibran.widgets.utils.mapper.ApiResult
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(private val quoteService: QuoteService) :
    QuoteRepository {
    override suspend fun getRandomQuote(): ApiResult<Quote> {
        return ApiResult.handleApiResult { quoteService.getRandomQuote() }
    }

    override suspend fun getCategorizedQuote(category: Category): ApiResult<Quote> {
        return ApiResult.handleApiResult { quoteService.getCategorizedQuote(category = category.category) }
    }
}