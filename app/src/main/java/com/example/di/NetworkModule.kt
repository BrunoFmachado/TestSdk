package com.example.di

import com.example.data.remote.MercadoPagoService
import com.example.domain.repository.PaymentRepository
import com.example.data.dataRepository.PaymentRepositoryImpl
import com.example.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val AUTH_TOKEN =
        "TEST-7779648963746966-033019-400a5b7100c58c8d915b7f06bec7406c-2359417725"

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $AUTH_TOKEN")
                .build()
            chain.proceed(request)
        }


        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideMercadoPagoService(retrofit: Retrofit): MercadoPagoService =
        retrofit.create(MercadoPagoService::class.java)

    @Provides
    @Singleton
    fun providePaymentRepository(service: MercadoPagoService): PaymentRepository =
        PaymentRepositoryImpl(service)
}
