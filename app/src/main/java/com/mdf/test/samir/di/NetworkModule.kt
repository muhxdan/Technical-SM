package com.mdf.test.samir.di

import com.mdf.test.samir.BuildConfig
import com.mdf.test.samir.data.datasource.remote.network.ApiService
import com.mdf.test.samir.utils.Constants.BASE_NETWORK_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the OkHttpClient instance for making HTTP requests.
     * It includes an interceptor to log HTTP request/response details.
     *
     * @return OkHttpClient instance configured with logging and other settings.
     */
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    /**
     * Provides the Retrofit instance to make network requests.
     * It is configured with the OkHttpClient and Gson converter for JSON parsing.
     *
     * @param okHttpClient The OkHttpClient instance used for HTTP requests.
     * @return Retrofit instance to interact with the API.
     */
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_NETWORK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the ApiService instance to interact with the API.
     * It allows calling endpoints defined in the ApiService interface.
     *
     * @param retrofit The Retrofit instance to create the ApiService.
     * @return ApiService instance to perform API requests.
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}