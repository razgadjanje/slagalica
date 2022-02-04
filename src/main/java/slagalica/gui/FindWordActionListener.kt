package slagalica.gui

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class FindWordActionListener(private val owner: SlagalicaGUI) : ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        owner.updateTheList()
    }
}