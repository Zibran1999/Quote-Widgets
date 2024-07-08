package com.zibran.widgets.utils.mapper

data class SuccessResponse<T : Any>(val data: T) : ApiResult<T>
