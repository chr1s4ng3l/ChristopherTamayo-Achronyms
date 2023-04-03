package com.tamayo.christophertamayo_achronyms.di

import com.squareup.moshi.Moshi
import com.tamayo.christophertamayo_achronyms.rest.ServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModulo {

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ServiceAPI.BASE_PATH)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient).build()

    @Provides
    fun providesOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesServiceAPI(retrofit: Retrofit): ServiceAPI =
        retrofit.create(ServiceAPI::class.java)


    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO


}