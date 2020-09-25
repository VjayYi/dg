package com.dana.gaib.ga.ib.application

import android.annotation.SuppressLint
import android.app.Application

/**
 */
class MyApplication : Application() {

    companion object {
        /** MyApplication 实例对象 */
        @SuppressLint("StaticFieldLeak")
        lateinit var INSTANCE: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        // 保存实例对象
        INSTANCE = this
    }

}