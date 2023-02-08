package com.fireblade.filehierarchy

import android.app.Application

class FileHierarchyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter.start(this)
    }
}