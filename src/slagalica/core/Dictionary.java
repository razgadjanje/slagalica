package slagalica.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private List<String>	dictionary	= new ArrayList<String>();

	public Dictionary(String dictionaryPath) throws FileNotFoundException {
		String inputLine;
		BufferedReader inputFile;
		try {
			inputFile = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(dictionaryPath), "utf-8"));
			while ((inputLine = inputFile.readLine()) != null) {
				dictionary.add(inputLine);
			}
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public boolean containsWord(String word, String letters) {
		ArrayList<Character> localLetters = new ArrayList<Character>();
		for (char tempChar : letters.toLowerCase().toCharArray()) {
			localLetters.add(tempChar);
		}
		for (char tempChar : word.toCharArray()) {
			if (!localLetters.remove(Character.valueOf(tempChar))) {
				return false;
			}

		}
		return true;
	}

	public List<String> findAllWords(String letters) {
		ArrayList<String> localList = new ArrayList<String>();
		for (String word : dictionary) {
			if (letters == null || containsWord(word, letters)) {
				localList.add(word);
			}
		}
		return localList;
	}

}
