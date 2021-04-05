package com.srg.codetestrickmorty.common.errors

import com.srg.codetestrickmorty.R
import retrofit2.HttpException

fun Throwable.toDomain() = when (this) {
    is HttpException -> {
        response()?.errorBody()?.string()?.run {
            try {
                parseHttpError<APIError>(this@toDomain.code(), this@run)
            } catch (e: Exception) {
                //ignore
                RuntimeException(this)
            }
        } ?: NetworkException(code(), APIErrorCode.UNKNOWN)
    }
    else -> this
}

fun Throwable.toDialogError(): DialogErrorViewEntity =
    when ((this as? NetworkException)?.apiCode) {
        APIErrorCode.UNKNOWN ->
            DialogErrorViewEntity(
                dialogMessage = R.string.error_dialog_unknow
            )
        else -> DialogErrorViewEntity(dialogMessage = R.string.error_dialog_unknow)
    }
