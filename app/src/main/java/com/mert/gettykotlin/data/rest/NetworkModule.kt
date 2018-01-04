package com.mert.gettykotlin.data.rest

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Mert Kilic on 24.10.2017.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter(API_QUERY, API_KEY)
                    .build()

            val newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl)
                    .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(Cache(context.cacheDir, CACHE_SIZE))

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideRestService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

    companion object {

        private val BASE_URL = ""
        private val API_QUERY = "api_key"
        private val API_KEY = ""

        private val CACHE_SIZE = (10 * 1024 * 1024).toLong()
        private val CONNECT_TIMEOUT = 15
        private val WRITE_TIMEOUT = 60
        private val READ_TIMEOUT = 60
    }
}
