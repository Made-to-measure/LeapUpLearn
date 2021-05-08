package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoAbout extends JDialog {
	/**
	 * JDialog: Zeigt die Credits des Projekts an im LUL Design
	 * 
	 * @author Christian
	 */

	private final JPanel contentPanel = new JPanel();

	public InfoAbout() {
		//Layout festlegen
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Füge Methoden aus der "Main" ein
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg"))); //Projektlogo setzen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Fenster mit X schließen
		setTitle("Über...");  //Titel setzen
		setResizable(false);  //Größe des Fenster kann nicht geändert werden
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextPane Info = new JTextPane(); 
			String InfoText = "LeapUpLearn \n"  //Programminformationen als String speichern
					+ "Version 0.0.x \n"		//Über den Konstruktor/setText-Methode von JTextPane 
					+ "\n"						//können keine Formatierungen gesetzt werden
					+ "Team: \n"
					+ "Janis Bulmahn, Jannik Harmeling, Christian Koch";
			Info.setText(InfoText); //String in TextPane übernehmen
			contentPanel.add(Info); //TextPane Info hinzufügen
			JPanel buttonPane = new JPanel();
			//Layout für den Btn-Pane definieren
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{	//OK-Btn mit Funktion versehen und hinzufügen
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); //Fenster mit OK-Btn schließen
					}
				});
				buttonPane.add(okButton);
			}

		}
		//Meldung in der Bildschirmmitte anzeigen
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, 
				   ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
	}

}
