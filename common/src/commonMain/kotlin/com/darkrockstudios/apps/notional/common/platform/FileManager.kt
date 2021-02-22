package com.darkrockstudios.apps.notional.common.platform

import java.io.File
import java.io.FileReader
import java.io.FileWriter

abstract class FileManager {
    protected abstract fun getSystemUserDirectory(): File

    private fun getRootDirectory() = File(getSystemUserDirectory(), "notional")
    private fun getProjectDirectory(projectName: String) = File(getRootDirectory(), projectName)

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

    fun getProjects(): List<String> {
        val projects = mutableListOf<String>()

        val rootDir = getRootDirectory()
        if (rootDir.isDirectory) {
            val children = rootDir.listFiles()
            children?.forEach { child ->
                if (child.isDirectory) {
                    projects.add(child.name)
                }
            }
        }

        return projects
    }

    private fun validateProjectName(projectName: String): Boolean {
        return projectName.isNotEmpty()
    }

    fun createProject(projectName: String): Boolean {
        return if (validateProjectName(projectName)) {
            val projectDirectory = getProjectDirectory(projectName)
            if (projectDirectory.exists()) {
                false
            } else {
                projectDirectory.mkdirs()
            }
        } else {
            false
        }
    }

    private fun getSectionsDirectory(projectName: String): File {
        val dir = File(getProjectDirectory(projectName), "sections")
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                println("Failed to make sections directory")
            }
        }
        return dir
    }

    fun getSections(projectName: String): List<String> {
        val sections = mutableListOf<String>()

        val sectionsDir = getSectionsDirectory(projectName)
        sectionsDir.listFiles()?.forEach { file ->
            if (file.isFile) {
                sections.add(file.name)
            }
        }

        return sections
    }

    private fun validateSectionName(sectionName: String): Boolean {
        return sectionName.isNotEmpty()
    }

    fun createSection(projectName: String, sectionName: String): Boolean {
        return if (validateSectionName(sectionName)) {
            val sectionsDir = getSectionsDirectory(projectName)
            val sectionFile = File(sectionsDir, sectionName)
            if (!sectionFile.exists()) {
                sectionFile.createNewFile()
            } else {
                false
            }
        } else {
            false
        }
    }
}