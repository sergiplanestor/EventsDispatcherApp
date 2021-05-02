package com.revolhope.data.injection

import android.os.Build
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.revolhope.data.BuildConfig
import com.revolhope.data.feature.event.datasource.EventNetworkApi
import com.revolhope.data.feature.event.datasource.EventNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RestModule {

    @Provides
    @Singleton
    fun providesEventNetworkApi(): EventNetworkApi =
        Retrofit.Builder()
            .baseUrl(EventNetworkDataSource.EVENTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor())
            }.build())
            .build()
            .create(EventNetworkApi::class.java)
}
