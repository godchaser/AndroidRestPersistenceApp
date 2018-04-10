package io.foreach.androidrestpersistenceapp.rest

import io.foreach.androidrestpersistenceapp.database.TransactionHandler
import io.foreach.androidrestpersistenceapp.database.model.Country
import io.foreach.androidrestpersistenceapp.database.model.Tourist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


object VisitorRepository {
    val visitorService by lazy {
        VisitorService.create()
    }

    fun getCountries() =
            visitorService.getCountries()
                    .flatMapCompletable { countries ->
                        TransactionHandler.saveRecords(countries)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { Timber.d("Rest persistence actions completed") },
                            { error -> Timber.e("$error") }
                    )

    fun getTourists() =
            visitorService.getTourists()
                    .flatMapCompletable { tourists ->
                        TransactionHandler.saveRecords(tourists)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { Timber.d("Rest persistence actions completed") },
                            { error -> Timber.e("$error") }
                    )

    fun getCountryTourists() =
            visitorService.getCountries()
                    .flatMapCompletable { countries ->
                        visitorService.getTourists()
                                .flatMapCompletable { tourists ->
                                    val countryTourists = listOf(*countries.toTypedArray(), *tourists.toTypedArray())
                                    TransactionHandler.saveRecords(countryTourists)
                                }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { Timber.d("Rest persistence actions completed, countries in db ${Country.getAll()} and tourists in db ${Tourist.getAll()}") },
                            { error -> Timber.e("$error") }
                    )
}