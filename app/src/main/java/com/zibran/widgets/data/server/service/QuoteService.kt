package com.zibran.widgets.data.server.service

import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.model.response.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("quotes")
    suspend fun getRandomQuote(): Response<Quote>

    @GET("quotes/")
    suspend fun getCategorizedQuote(@Query("category") category: String): Response<Quote>
}