package io.foreach.androidrestpersistenceapp.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
class VisitorService {
    companion object {
        private const val baseUrl: String = "http://localhost:8080/"
        fun create(): VisitorApi {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(FakeInterceptor())
                    .build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            return retrofit.create(VisitorApi::class.java)
        }
    }
}