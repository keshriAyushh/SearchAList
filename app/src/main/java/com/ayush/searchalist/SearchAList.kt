package com.ayush.searchalist

import android.app.Application
import com.ayush.searchalist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchAList: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(this@SearchAList)
        }
    }
}