package slagalica.core;

import java.util.Comparator;

public class ReverseStringComparator implements Comparator<String>{
	@Override
	public int compare(String first, String second) {
		if (first.length() < second.length()) {
			return 1;
		} else if (first.length() > second.length()) {
			return -1;
		}
		return -1 * first.compareTo(second);
	}
}
