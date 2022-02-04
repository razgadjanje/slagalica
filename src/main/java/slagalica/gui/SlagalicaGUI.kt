@file:Suppress("SpellCheckingInspection")

package slagalica.gui

import slagalica.core.Dictionary
import slagalica.loadResource
import java.awt.*
import javax.imageio.ImageIO
import javax.swing.*

class SlagalicaGUI {
    private val serbianDictionary = Dictionary(DICTIONARY_PATH)
    private val wordListModel = WordListModel().apply {
        values = serbianDictionary.findAllWords(null)
    }

    private val textField = JTextField()
    private val frmSlagalica = initialize(textField, wordListModel)

    private fun initialize(searchField: JTextField, model: WordListModel): JFrame = JFrame().apply {
        title = "Slagalica"
        setBounds(100, 100, 578, 405)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        iconImage = ImageIO.read(loadResource(ICON_PATH))

        contentPane.apply {
            val findWords = FindWordActionListener(this@SlagalicaGUI)

            layout = GridBagLayout().apply {
                columnWidths = intArrayOf(100, 300, 100, 0)
                rowHeights = intArrayOf(0, 0, 0)
                columnWeights = doubleArrayOf(0.0, 2.0, 0.0, Double.MIN_VALUE)
                rowWeights = doubleArrayOf(0.0, 1.0, Double.MIN_VALUE)
            }

            add(JLabel("Унесите слова:"), GridBagConstraints().apply {
                insets = Insets(16, 16, 16, 16)
                anchor = GridBagConstraints.WEST

            })

            add(searchField.apply {
                columns = 10
                addActionListener(findWords)
                border = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 1, true), BorderFactory.createEmptyBorder(8, 8, 8, 8)
                )
            }, GridBagConstraints().apply {
                insets = Insets(16, 0, 16, 0)
                fill = GridBagConstraints.BOTH

            })

            add(JButton("Пронађи речи").apply {
                addActionListener(findWords)
            }, GridBagConstraints().apply {
                insets = Insets(16, 16, 16, 16)
                fill = GridBagConstraints.BOTH
            })

            add(JScrollPane().apply {
                viewportBorder = null
                border = BorderFactory.createLineBorder(Color.BLACK, 1, true)

                setViewportView(JList<String>().apply {
                    alignmentX = JList.CENTER_ALIGNMENT
                    cellRenderer = object : JLabel(), ListCellRenderer<String> {
                        override fun getListCellRendererComponent(
                            list: JList<out String>?,
                            value: String?,
                            index: Int,
                            isSelected: Boolean,
                            cellHasFocus: Boolean
                        ): Component {
                            border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
                            text = value
                            return this
                        }
                    }
                    this.model = model
                    border = null
                })
            }, GridBagConstraints().apply {
                fill = GridBagConstraints.BOTH
                gridwidth = 3
                insets = Insets(0, 8, 8, 8)
                gridx = 0
                gridy = 1
            })
        }
    }

    fun updateTheList() {
        EventQueue.invokeLater {
            try {
                wordListModel.values = serbianDictionary.findAllWords(textField.text)
                wordListModel.refresh()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val ICON_PATH = "images/app_icon.png"
        private const val DICTIONARY_PATH = "data/sr-cyrl.wl"

        @JvmStatic
        fun main(args: Array<String>) {
            Taskbar.getTaskbar().iconImage = ImageIO.read(loadResource(ICON_PATH))
            EventQueue.invokeLater {
                val window = SlagalicaGUI()
                window.frmSlagalica.isVisible = true
            }
        }
    }
}