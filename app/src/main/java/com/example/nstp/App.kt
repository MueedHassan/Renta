package com.example.nstp

import android.app.Application
import com.example.home.addnewproperty.ui.Components.ui.di.mainModule
import com.example.home.addnewproperty.ui.Components.ui.di.myModule
import com.example.home.chatbot.di.chatModule
import com.example.home.chatbot.di.networkModule
import com.google.firebase.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainModule, myModule, networkModule, chatModule)
        }
    }
}