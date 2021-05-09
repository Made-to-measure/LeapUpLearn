package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

	private final JPanel contentPanel = new JPanel();

	public InfoAbout() {
		//Layout festlegen
		setBounds(100, 100, 320, 220);
		setMinimumSize(new Dimension(320, 220));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//F�ge Methoden aus der "Main" ein
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg"))); //Projektlogo setzen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Fenster mit X schlie�en
		setTitle("\u00dcber...");  //Titel setzen
		setResizable(true);  //Gr��e des Fenster kann nicht ge�ndert werden
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextPane Info = new JTextPane(); 
			String InfoText = "LeapUpLearn \n"  //Programminformationen als String speichern
					+ "Version 0.0.x \n"		//�ber den Konstruktor/setText-Methode von JTextPane 
					+ "\n"						//k�nnen keine Formatierungen gesetzt werden
					+ "Team: \n"
					+ "Janis Bulmahn, Jannik Harmeling, Christian Koch ";
			Info.setText(InfoText); //String in TextPane �bernehmen
			contentPanel.add(Info); //TextPane Info hinzuf�gen
			JPanel buttonPane = new JPanel();
			//Layout f�r den Btn-Pane definieren
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{	//OK-Btn mit Funktion versehen und hinzuf�gen
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); //Fenster mit OK-Btn schlie�en
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
