package slagalica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindWordActionListener implements ActionListener {

    private SlagalicaGUI owner;

    public FindWordActionListener(SlagalicaGUI owner) {
        super();
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        owner.updateTheList();
    }

}
