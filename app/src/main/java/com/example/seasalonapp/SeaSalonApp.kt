package com.example.seasalonapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.seasalonapp.presentation.viewmodel.mainservices.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class SeaSalonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext


        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@SeaSalonApp)
            modules(vmModule)
        }
    }

    private val vmModule = module {
        viewModel { HomeViewModel(get()) }
    }



    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}