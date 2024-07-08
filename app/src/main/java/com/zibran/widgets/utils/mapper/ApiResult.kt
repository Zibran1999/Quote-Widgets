package com.zibran.widgets.utils.mapper

import retrofit2.HttpException
import retrofit2.Response

sealed interface ApiResult<T : Any> {
    companion object {
        suspend fun <T : Any> handleApiResult(execute: suspend () -> Response<T>): ApiResult<T> {
            return try {
                val response = execute()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    SuccessResponse(data = body)
                } else {
                    ErrorResponse(code = response.code(), message = response.message())
                }
            } catch (e: HttpException) {
                ErrorResponse(code = e.code(), message = e.message())
            } catch (e: Throwable) {
                ExceptionResponse(e)
            }
        }
    }
}