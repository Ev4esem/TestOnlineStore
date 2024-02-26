package com.example.testonlinestore.utils

import android.database.sqlite.SQLiteException
import android.os.NetworkOnMainThreadException
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.text.ParseException


sealed interface Resource<out T> {
    data class Success<T>(val data : T) : Resource<T>
    data class Error(
        val exception : Throwable,
        val message : String
        ) : Resource<Nothing>

     object Loading : Resource<Nothing>
}


// Автоматическое обновление трех states
fun <T> Flow<T>.asResult() : Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> { Resource.Success(it) }
        .onStart { emit(Resource.Loading) }
        .catch { ex ->
            emit(
                Resource.Error(
                    exception = ex,
                    message = handleException(ex)
                )
            )
        }
}

suspend fun <T> Flow<T>.collectAsResult(
    onSuccess : suspend (T) -> Unit = {},
    onError : suspend (exception : Throwable?, message : String?) -> Unit = {_ ,_ ->},
    onLoading : () -> Unit = {}
) {
    asResult().collect { result ->

        when(result) {
            is Resource.Success -> onSuccess(result.data)
            is Resource.Error -> {
                val httpsError = handleException(result.exception)
                onError(result.exception, httpsError)
            }

            Resource.Loading -> onLoading()

        }

    }
}


@Composable
fun <T> ObserveEffect(flow : Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}

// Обработка ошибок
fun handleException(exception : Throwable?) : String {
    return when(exception) {
        is HttpException -> parseHttpException(exception)
        is IOException -> "Произошла ошибка при загрузке данных, проверьте подключение к сети"
        is JsonSyntaxException,is JsonParseException -> "Ошибка при обработке данных"
        is NetworkOnMainThreadException -> "Сетевая операция на главном потоке"
        is SecurityException -> "Проблема с безопасностью"
        is IllegalArgumentException -> "Некорректные аргументы"
        is SQLiteException -> "Ошибка базы данных"
        is OutOfMemoryError -> "Недостаточно памяти"
        is ParseException -> "Ошибка при анализе данных"
        else -> "Неизвестная ошибка: ${exception?.localizedMessage}"
    }
}

private fun parseHttpException(exception : HttpException) : String {

    val statusCode = exception.code()
    val errorBody = exception.response()?.errorBody()?.string()

    val defaultErrorMessage = "Произошла ошибка, пожалуйста попробуйте позже."

    return when(statusCode) {
        HttpURLConnection.HTTP_BAD_REQUEST -> errorBody ?: "Неверный запрос."
        HttpURLConnection.HTTP_UNAUTHORIZED -> errorBody ?: "Пустой или неправильный токен."
        HttpURLConnection.HTTP_PAYMENT_REQUIRED,
        HttpURLConnection.HTTP_FORBIDDEN -> errorBody ?: "Превышен лимит запросов."

        HttpURLConnection.HTTP_NOT_FOUND -> errorBody ?: "Ресурс не найден."
        429 -> errorBody ?: "Слишком много запросов. Общий лимит - 20 запросов в секунду."
        in 300..399 -> errorBody ?: "Ошибка клиента: $statusCode."
        in 500..599 -> "Ошибка сервера: $statusCode."
        else -> defaultErrorMessage
    }

}

