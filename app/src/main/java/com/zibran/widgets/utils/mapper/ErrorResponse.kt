package com.zibran.widgets.utils.mapper

class ErrorResponse<T : Any>(val code: Int, val message: String?) : ApiResult<T>
