package slagalica

import java.io.InputStream

fun loadResource(path: String): InputStream {
    val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(path)
    requireNotNull(resource) { "Resource $path not found" }
    return resource
}