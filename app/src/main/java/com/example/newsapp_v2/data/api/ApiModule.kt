package com.example.newsapp_v2.data.api

import android.util.Log
import com.example.newsapp_v2.data.model.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
   val logging =  HttpLoggingInterceptor {
            Log.e("Api",it)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(httpClient)
            .baseUrl("https://newsapi.org/")
            .build()
    }

    @Provides
    fun provideWebServices(retrofit: Retrofit):WebServices{
        return retrofit.create(WebServices::class.java)
    }




}