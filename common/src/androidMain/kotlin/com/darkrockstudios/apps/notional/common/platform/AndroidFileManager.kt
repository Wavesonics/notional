package com.darkrockstudios.apps.notional.common.platform

import android.content.Context
import java.io.File


class AndroidFileManager(private val appContext: Context) : FileManager() {
    override fun getSystemUserDirectory(): File = appContext.filesDir
}