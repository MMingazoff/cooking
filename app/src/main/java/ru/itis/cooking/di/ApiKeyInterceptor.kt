package ru.itis.cooking.di

import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.cooking.core.data.util.Constants

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder().addQueryParameter(
            name = "apiKey", value = Constants.API_KEY
        ).build()
        val newRequest = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}
