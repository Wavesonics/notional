package com.darkrockstudios.apps.notional.common.platform

import java.io.File
import java.io.FileReader
import java.io.FileWriter

class DesktopSaveManager : SaveManager() {
    val projectDir = "notional_test"

    private fun getFile(path: String): File {
        val userDirPath = System.getProperty("user.home")
        val userDir = File(userDirPath)
        val projectDir = File(userDir, projectDir)

        if (!projectDir.exists()) {
            projectDir.mkdirs()
        }

        val file = File(projectDir, path)
        return file
    }

    override fun writeToFile(project: String, path: String, content: String) {
        val file = getFile(path)

        if (!file.exists()) {
            val parentDir = file.parentFile
            println(parentDir.absolutePath)
            if (!parentDir.exists()) {
                parentDir.mkdirs()
            }

            if (!file.createNewFile()) {
                println("Failed to make new file: ${file.absolutePath}")
            } else {
                println("Creation File: ${file.absolutePath}")
            }
        }

        FileWriter(file, false).use { writer ->
            writer.write(content)
            writer.flush()
        }

        println("writing to file: ${file.absolutePath}")
        println("content written: $content")
    }

    override fun readFile(project: String, path: String): String {
        val file = getFile(path)
        return if (file.exists()) {
            FileReader(file).use { reader ->
                reader.readText()
            }
        } else {
            ""
        }
    }

    override fun getProjects(): Array<String> {
        TODO("Not yet implemented")
    }
}