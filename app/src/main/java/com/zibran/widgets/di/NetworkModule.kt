package com.zibran.widgets.di

import com.zibran.widgets.BuildConfig
import com.zibran.widgets.data.server.service.QuoteService
import com.zibran.widgets.utils.constants.ErrorConstants
import com.zibran.widgets.utils.constants.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                try {
                    chain.proceed(chain.request().newBuilder().also { requestBuilder ->
                        requestBuilder.addHeader("Content-Type", "application/json")
                        requestBuilder.addHeader(
                            NetworkConstants.X_API_KEY, "WGp9LsZq+HcJ++iBWHTT7A==eOajalCbvM3qvi9A"
                        )

                    }.build())
                } catch (e: IOException) {
                    val errorMsg: String = when (e) {
                        is SocketTimeoutException -> ErrorConstants.SOCKET_TIMEOUT_EXCEPTION
                        else -> ErrorConstants.IO_EXCEPTION
                    }

                    Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1)
                        .code(200).message("OK").body("${
                            JSONObject().run {
                                put("status", 0)
                                put("message", errorMsg)
                            }
                        }".toByteArray().toResponseBody("application/json".toMediaType())).build()
                }
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    client.addInterceptor(interceptor)
                }

            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

    }

    @Provides
    @Singleton
    fun provideQuoteService(retrofit: Retrofit): QuoteService {
        return retrofit.create(QuoteService::class.java)
    }
}