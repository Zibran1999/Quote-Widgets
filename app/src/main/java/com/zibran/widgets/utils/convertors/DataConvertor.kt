package com.zibran.widgets.utils.convertors

import com.zibran.widgets.data.local.entity.Quote
import com.zibran.widgets.data.server.model.response.QuoteItem

object DataConvertor {

    fun QuoteItem.toQuote(): Quote {
        return Quote(author = author, category = category, quote = quote)
    }

    fun Quote.toQuoteItem(): QuoteItem {
        return QuoteItem(author = author, category = category, quote = quote)
    }
}