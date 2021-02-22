package com.darkrockstudios.apps.notional.common.platform

import java.io.File
import java.io.FileReader
import java.io.FileWriter

abstract class SaveManager {
    protected abstract fun getRootDirectory(): File

    private fun getFile(project: String, path: String): File {
        val userDir = getRootDirectory()
        val projectDir = File(userDir, project)

        if (!projectDir.exists()) {
            projectDir.mkdirs()
        }

        val file = File(projectDir, path)
        return file
    }

    fun writeToFile(project: String, path: String, content: String) {
        val file = getFile(project, path)

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

    fun readFile(project: String, path: String): String {
        val file = getFile(project, path)
        return if (file.exists()) {
            FileReader(file).use { reader ->
                reader.readText()
            }
        } else {
            ""
        }
    }

    fun getProjects(): Array<String> {
        TODO("Not yet implemented")
    }
}