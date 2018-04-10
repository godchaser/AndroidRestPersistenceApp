package io.foreach.androidrestpersistenceapp.database.model

import com.raizlabs.android.dbflow.annotation.ConflictAction
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.structure.BaseModel
import io.foreach.androidrestpersistenceapp.database.AppDatabase

@Table(name = "tourist", database = AppDatabase::class, allFields = true, insertConflict = ConflictAction.REPLACE)
data class Tourist(
        @PrimaryKey
        var ID: String = "",
        var Citizenship: String = "",
        var Gender: String = "",
        var StayFrom: String = "",
        var TimeStayFrom: String = "",
        var TouristName: String = "",
        var TouristSurname: String = "") : BaseModel() {
    companion object {
        fun getAll() = (select from Tourist::class).list
    }
}