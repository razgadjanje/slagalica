package slagalica.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import slagalica.core.Dictionary;

public class SlagalicaGUI {

	private static final String	ICON_PATH		= "images/icon.png";
	private static final String	DICTIONARY_PATH	= "data/sr-cyrl.wl";

	private JFrame				frmSlagalica;
	private JTextField			textField;

	private Dictionary			serbianDictionary;
	private WordListModel		wordListModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlagalicaGUI window = new SlagalicaGUI();
					window.frmSlagalica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws FileNotFoundException
	 */
	public SlagalicaGUI() throws FileNotFoundException {
		initialize();

		serbianDictionary = new Dictionary(DICTIONARY_PATH);
		wordListModel.setValues(serbianDictionary.findAllWords(null));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSlagalica = new JFrame();
		frmSlagalica.setTitle("Slagalica");
		frmSlagalica.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		frmSlagalica.setBounds(100, 100, 578, 405);
		frmSlagalica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionListener findWords = new FindWordActionListener(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 300, 100, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 2.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frmSlagalica.getContentPane().setLayout(gridBagLayout);

		JLabel lblUnesiteSlova = new JLabel("Унесите слова:");
		lblUnesiteSlova.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblUnesiteSlovaZa = new GridBagConstraints();
		gbc_lblUnesiteSlovaZa.insets = new Insets(0, 5, 5, 5);
		gbc_lblUnesiteSlovaZa.anchor = GridBagConstraints.WEST;
		gbc_lblUnesiteSlovaZa.gridx = 0;
		gbc_lblUnesiteSlovaZa.gridy = 0;
		frmSlagalica.getContentPane().add(lblUnesiteSlova, gbc_lblUnesiteSlovaZa);

		textField = new JTextField();
		textField.addActionListener(findWords);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(2, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		frmSlagalica.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Пронађи речи");
		btnNewButton.addActionListener(findWords);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 5, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		frmSlagalica.getContentPane().add(btnNewButton, gbc_btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		frmSlagalica.getContentPane().add(scrollPane, gbc_scrollPane);

		JList<String> list = new JList<String>();
		wordListModel = new WordListModel();
		list.setAlignmentX(JList.CENTER_ALIGNMENT);
		list.setModel(wordListModel);
		scrollPane.setViewportView(list);
		list.setBorder(null);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		try {
			frmSlagalica.setIconImage(ImageIO.read(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(ICON_PATH)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateTheList() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wordListModel.setValues(serbianDictionary
							.findAllWords(textField.getText()));
					wordListModel.refresh();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
