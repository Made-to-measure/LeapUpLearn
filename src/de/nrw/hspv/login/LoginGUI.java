package de.nrw.hspv.login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import de.nrw.hspv.ui.App;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame{
	
	JTextField eingabeName;														//Felder für Benutzername und Passwort verwaltbar machen
	JPasswordField eingabePasswort;
	
	public LoginGUI() {
		super("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		
		JPanel panelOben = new JPanel();										//PanelOben für Eingabe
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		panelOben.setLayout(new GridLayout(2,2,0,0));							//GridLayout für einzelne Elemente des Logins
		
		
		JLabel labelName = new JLabel("Nutzername");
		panelOben.add(labelName);
		eingabeName = new JTextField();
		panelOben.add(eingabeName);
				
		JLabel labelPasswort = new JLabel("Passwort");
		panelOben.add(labelPasswort);
		eingabePasswort = new JPasswordField();
		panelOben.add(eingabePasswort);		
		
		add(panelOben, BorderLayout.CENTER);
		
		JPanel panelUnten = new JPanel();										//panelUnten für Buttons
		
		JButton anmelden = new JButton("Login");								//Button für Login
		anmelden.addActionListener(new ActionListener() {						

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub								//löst Methode anmelden in Klasse Login aus
				if(Login.anmelden(eingabeName.getText(), eingabePasswort.getPassword())) {	//Text aus Namens- und Passwortfeld wird übergeben
					LoginGUI.super.dispose();									//Bei erfolgreichem Login wird LoginGUI geschlossen
				}
			}
			
		});
		
		JButton registrieren = new JButton("Registrieren");						//Button für Registrierung
		registrieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub								//löst Methode registrieren der Klasse Login aus
				Login.registrieren(eingabeName.getText(), eingabePasswort.getPassword());	//Text aus Namens- und Passwortfeld wird übergeben
			}
			
		});
		panelUnten.add(anmelden);
		panelUnten.add(registrieren);
		add(panelUnten, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
		//Fenster wird in der Mitte des Bildschirms dargestellt
	}
}
