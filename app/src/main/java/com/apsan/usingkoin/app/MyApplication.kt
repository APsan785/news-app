package com.apsan.usingkoin.app

import android.app.Application
import com.apsan.usingkoin.di.apiModule
import com.apsan.usingkoin.di.repositoryModule
import com.apsan.usingkoin.di.retrofitModule
import com.apsan.usingkoin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }

}