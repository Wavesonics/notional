package com.darkrockstudios.apps.notional.common.platform

import android.content.Context
import java.io.File


class AndroidSaveManager(private val appContext: Context) : SaveManager() {
    override fun getRootDirectory(): File = appContext.filesDir
}