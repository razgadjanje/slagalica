package slagalica.gui

import slagalica.core.ReverseStringComparator
import javax.swing.AbstractListModel

internal class WordListModel : AbstractListModel<String>() {
    var values: List<String> = ArrayList()
        set(value) {
            field = value.sortedWith(ReverseStringComparator())
        }

    override fun getSize(): Int {
        return values.size
    }

    override fun getElementAt(index: Int): String {
        val temp = values[index]
        return String.format("%s [%d]", temp, temp.length)
    }

    fun refresh() {
        fireContentsChanged(this, 0, values.size - 1)
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}