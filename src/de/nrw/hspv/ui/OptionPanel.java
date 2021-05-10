package de.nrw.hspv.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.util.logging.Logger;

import de.nrw.hspv.ui.Mainframe.MainPanel;

/**
 * Klasse zur Anzeige der Optionen
 * <br><br>
 * erstmal nur Logging
 * 
 * @author Christian
 * @version 1.0
 *
 */
public class OptionPanel extends JPanel {

	public OptionPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		JPanel Options = new JPanel();
		JLabel lblLoggingOptions = new JLabel("Logging Level auswählen");
		add(lblLoggingOptions);
		
		ButtonGroup btnGrpLogging = new ButtonGroup();
		
		//RadioBtn optisch absetzen
		Component verticalStrut = Box.createVerticalStrut(5);
		add(verticalStrut);
		
		JRadioButton rdbtnLoggingLOff = new JRadioButton("Aus");
		add(rdbtnLoggingLOff);
		btnGrpLogging.add(rdbtnLoggingLOff);
		
		JRadioButton rdbtnLoggingLInfo = new JRadioButton("Info");
		add(rdbtnLoggingLInfo);
		btnGrpLogging.add(rdbtnLoggingLInfo);
		
		JRadioButton rdbtnLoggingLSevere = new JRadioButton("Severe");
		add(rdbtnLoggingLSevere);
		btnGrpLogging.add(rdbtnLoggingLSevere);
		
		//OK-Btn optisch absetzen		
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		add(verticalStrut_1);

		JButton btnOptionOK = new JButton("OK");
		add(btnOptionOK);
		btnOptionOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			 if (rdbtnLoggingLOff.isSelected()==true) {
				 App.setLogLevel(Level.OFF);
			 }
			 else if (rdbtnLoggingLInfo.isSelected()==true) {
				 App.setLogLevel(Level.INFO);
			 }
			 else if (rdbtnLoggingLSevere.isSelected()==true) {
				 App.setLogLevel(Level.SEVERE);
			 }
			 
			}
		});
	
	}
	
}