package io.foreach.androidrestpersistenceapp.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "VisitorDatabase"
    const val VERSION: Int = 1
}
