package com.example.newsapp.di

import com.example.newsapp.common.Const.BASE_URL
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.Remote
import com.example.newsapp.data.remote.ServiceGenerator
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.use_case.get_news.GetNewsUseCase
import com.reva.data.utils.json.SingleToArrayAdapter
import com.reva.data.utils.network.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    @Provides
//    @Singleton
//    fun provideNewsApi(): NewsApi {
//
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//            .create(NewsApi::class.java)
//    }



    @Provides
    @Singleton
    fun provideNewsRepository(remote: Remote): NewsRepository {
        return NewsRepositoryImpl(remote)
    }


    @Provides
    fun getNewsListByPaging(
        coroutineContext: CoroutineContext,
        newsRepository: NewsRepository
    ): GetNewsUseCase {
        return GetNewsUseCase(coroutineContext, newsRepository)
    }


    @Provides
    fun retrofit(
        moshiConverterFactory: MoshiConverterFactory,
        httpClient: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory,
        @Named("BaseUrl") baseUrl: String
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .client(httpClient)
            .build()
    }

    @Provides
    fun serviceGenerator(
        retrofit: Retrofit
    ): ServiceGenerator {
        return ServiceGenerator(retrofit)
    }

    @Provides
    @Named("BaseUrl")
    fun baseUrl() = BASE_URL


    @Provides
    fun moshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun moshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .add(SingleToArrayAdapter.INSTANCE)
            .build()
    }

    @Provides
    fun kotlinJsonAdapterFactory(): KotlinJsonAdapterFactory {
        return KotlinJsonAdapterFactory()
    }

    @Provides
    fun interceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "Reva")
                .build()
            chain.proceed(request)
        }
    }


    @Provides
    fun httpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .dispatcher(dispatcher)
            .build()
    }


    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    @Provides
    fun coroutineContext(): CoroutineContext = Dispatchers.IO
}