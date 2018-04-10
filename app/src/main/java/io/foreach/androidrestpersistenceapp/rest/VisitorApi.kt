package io.foreach.androidrestpersistenceapp.rest

import io.foreach.androidrestpersistenceapp.database.model.Country
import io.foreach.androidrestpersistenceapp.database.model.Tourist
import retrofit2.http.GET
import io.reactivex.Observable

interface VisitorApi {
    @GET("v1/api/tourists")
    fun getTourists(): Observable<List<Tourist>>

    @GET("v1/api/countries")
    fun getCountries(): Observable<List<Country>>
}
