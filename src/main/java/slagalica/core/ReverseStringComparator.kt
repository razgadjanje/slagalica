package slagalica.core

class ReverseStringComparator : Comparator<String> {
    override fun compare(first: String, second: String): Int = when {
        first.length < second.length -> 1
        first.length > second.length -> -1
        else -> -1 * first.compareTo(second)
    }
}