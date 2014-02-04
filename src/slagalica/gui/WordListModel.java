package slagalica.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import slagalica.core.ReverseStringComparator;

final class WordListModel extends AbstractListModel<String> {

	private static final long	serialVersionUID	= 1L;

	List<String>				values				= new ArrayList<String>();

	public void setValues(List<String> values) {
		this.values = values;
		Collections.sort(this.values, new ReverseStringComparator());
	}

	public int getSize() {
		return values.size();
	}

	public String getElementAt(int index) {
		String temp = values.get(index);
		return temp + " [" + temp.length() + "]";
	}

	public void refresh() {
		fireContentsChanged(this, 0, values.size() - 1);
	}
}
