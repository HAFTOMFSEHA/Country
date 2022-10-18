package com.example.android.country.network

import com.example.android.country.data.Country
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

/**
 * Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getCountries] method
 */
interface CountryApiService {
    /**
     * Returns a Coroutine [List] of [Country] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "countries.json" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("countries.json")
    fun getCountries() : Deferred<List<Country>>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CountryApi {
    val retrofitService: CountryApiService by lazy {
        retrofit.create(CountryApiService::class.java)
    }
}