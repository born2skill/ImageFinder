package pl.born2skill.imagefinder.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = request.url.toUrl().toString()
        println("Request URL: $url")

        return chain.proceed(request)
    }
}