package io.foreach.androidrestpersistenceapp.database

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.BaseModel
import io.reactivex.Completable

object TransactionHandler {
    fun saveRecords(records: List<BaseModel>) = Completable.create({ e ->
        val transaction = FlowManager.getDatabase(AppDatabase::class.java).beginTransactionAsync({
            records.map(BaseModel::save)
        })
                .success {
                    e.onComplete()
                }
                .error { _, error ->
                    e.onError(error)
                }
                .build()
        transaction.execute()
    })
}