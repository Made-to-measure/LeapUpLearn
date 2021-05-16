package de.nrw.hspv.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse zur Anzeige der benötigten Zeit
 */
public class TimePanel extends JPanel {
	
	static JLabel lblZeit;

	TimePanel(){
		this.setLayout(new BorderLayout(5, 5));
		this.add(lblZeit = new JLabel("00:00:00  "), BorderLayout.EAST);
		this.setVisible(true);
	}

	public JLabel getLblZeit() {
		return lblZeit;
	}

	public static void setLblZeit(String Zeit) {
		lblZeit.setText(Zeit);
	}
	
	
}