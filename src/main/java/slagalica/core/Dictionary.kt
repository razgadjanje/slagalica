package slagalica.core

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.currentThread
import java.util.*
import java.util.Locale.*

class Dictionary(dictionaryPath: String) {
    private val dictionary = BufferedReader(
        InputStreamReader(currentThread().contextClassLoader.getResourceAsStream(dictionaryPath)!!, "utf-8")
    ).readLines()


    fun findAllWords(letters: String?): List<String> = when(letters) {
        "" -> dictionary
        is String -> dictionary.filter { word -> containsWord(word, letters) }
        else -> dictionary
    }

    companion object {
        fun containsWord(word: String, letters: String): Boolean {
            val localLetters = ArrayList<Char>()
            for (tempChar in letters.lowercase(getDefault()).toCharArray()) {
                localLetters.add(tempChar)
            }
            for (tempChar in word.toCharArray()) {
                if (!localLetters.remove(Character.valueOf(tempChar))) {
                    return false
                }
            }
            return true
        }
    }
}