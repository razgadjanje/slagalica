package slagalica.core;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

	@Override
	public int compare(String first, String second) {
		if (first.length() < second.length()) {
			return -1;
		} else if (first.length() > second.length()) {
			return 1;
		}
		return first.compareTo(second);
	}

}
