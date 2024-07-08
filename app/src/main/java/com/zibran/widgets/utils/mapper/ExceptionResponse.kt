package com.zibran.widgets.utils.mapper

class ExceptionResponse<T : Any>(val e: Throwable) : ApiResult<T>
