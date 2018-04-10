package io.foreach.androidrestpersistenceapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import io.foreach.androidrestpersistenceapp.rest.VisitorRepository
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        FlowManager.init(FlowConfig.Builder(this).openDatabasesOnInit(true).build())
        disposable = VisitorRepository.getCountryTourists()
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }
}
