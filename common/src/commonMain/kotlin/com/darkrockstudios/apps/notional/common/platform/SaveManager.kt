package com.darkrockstudios.apps.notional.common.platform

abstract class SaveManager {
    abstract fun getProjects(): Array<String>
    abstract fun writeToFile(project: String, path: String, content: String)
    abstract fun readFile(project: String, path: String): String
}