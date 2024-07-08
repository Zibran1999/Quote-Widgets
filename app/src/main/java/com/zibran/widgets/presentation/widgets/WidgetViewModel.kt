package com.zibran.widgets.presentation.widgets

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zibran.widgets.data.local.dao.QuoteDao
import com.zibran.widgets.data.server.model.request.Category
import com.zibran.widgets.data.server.repository.QuoteRepository
import com.zibran.widgets.utils.constants.CategoryConstants
import com.zibran.widgets.utils.convertors.DataConvertor.toQuote
import com.zibran.widgets.utils.mapper.ErrorResponse
import com.zibran.widgets.utils.mapper.ExceptionResponse
import com.zibran.widgets.utils.mapper.SuccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(
    application: Application,
    private val repository: QuoteRepository,
    private val quoteDao: QuoteDao
) :
    AndroidViewModel(application = application) {

    var msg by mutableStateOf("")


    init {
        getCategoryWisedQuote()
    }

    private fun getRandomQuote(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            when (val response = repository.getRandomQuote()) {
                is SuccessResponse -> {
                    quoteDao.insertQuote(response.data[0].toQuote())
                    println("Hello")
                }

                is ErrorResponse -> {
                    msg = response.message.toString()
                }

                is ExceptionResponse -> {
                    msg = response.e.message.toString()
                }
            }
        }

    }

    private fun getCategoryWisedQuote(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            when (val response = repository.getCategorizedQuote(
                Category(
                    category = CategoryConstants.categories.random().toString()
                )
            )) {
                is SuccessResponse -> {
                    quoteDao.insertQuote(response.data[0].toQuote())
                    println("Hello")
                }

                is ErrorResponse -> {
                    msg = response.message.toString()
                }

                is ExceptionResponse -> {
                    msg = response.e.message.toString()
                }
            }
        }

    }
}