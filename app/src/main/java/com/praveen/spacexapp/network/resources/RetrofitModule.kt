package com.praveen.spacexapp.network.resources



import com.google.gson.GsonBuilder
import com.praveen.spacexapp.network.BASE_URL
import com.praveen.spacexapp.network.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.String
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * The provider class which used to get Retrofit Service
 */


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    public fun getNetWorkRepository(apiServices: RetrofitServices):NetworkRepository= NetworkRepository(apiServices)

    @Provides
    @Singleton
    public fun getService(): RetrofitServices =
        provideRetrofit(BASE_URL,provideOkHttpClient()).create(RetrofitServices::class.java)


    var gson=GsonBuilder().setLenient().create()
    private fun provideRetrofit(serviceBaseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(serviceBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    /**
     * method which returns [OkHttpClient] used to build retrofit service
     * @return [OkHttpClient]
     */
    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
           .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })

        return builder.build()
    }
}