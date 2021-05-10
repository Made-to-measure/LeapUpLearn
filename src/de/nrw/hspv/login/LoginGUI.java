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
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame{
	/**
	 * UI Klasse zur Anmeldung und Registrierung von Nutzern
	 * 
	 * @author Janis
	 * @version 1.0
	 */
	
	JTextField eingabeName;														//Felder f�r Benutzername und Passwort verwaltbar machen
	JPasswordField eingabePasswort;
	
	public LoginGUI() {
		super("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		final Image icon = Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg"));
		final Taskbar taskbar = Taskbar.getTaskbar();
		try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(icon);
        } catch (final UnsupportedOperationException e) {
//            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
//            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }
		
		setIconImage(icon);
		setLayout(new BorderLayout());
		
		JPanel panelOben = new JPanel();										//PanelOben f�r Eingabe
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		panelOben.setLayout(new GridLayout(2,2,0,0));							//GridLayout f�r einzelne Elemente des Logins
		
		
		JLabel labelName = new JLabel("Nutzername");
		panelOben.add(labelName);
		eingabeName = new JTextField();
		panelOben.add(eingabeName);
		eingabeName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(Login.anmelden(eingabeName.getText(), eingabePasswort.getPassword())) {	//Text aus Namens- und Passwortfeld wird �bergeben
						LoginGUI.super.dispose();									//Bei erfolgreichem Login wird LoginGUI geschlossen
					}	
					
				}
			}
		});
				
		JLabel labelPasswort = new JLabel("Passwort");
		panelOben.add(labelPasswort);
		eingabePasswort = new JPasswordField();
		panelOben.add(eingabePasswort);	
		eingabePasswort.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(Login.anmelden(eingabeName.getText(), eingabePasswort.getPassword())) {	//Text aus Namens- und Passwortfeld wird �bergeben
						LoginGUI.super.dispose();									//Bei erfolgreichem Login wird LoginGUI geschlossen
					}	
					
				}
			}
		});
		
		add(panelOben, BorderLayout.CENTER);
		
		JPanel panelUnten = new JPanel();										//panelUnten f�r Buttons
		
		JButton anmelden = new JButton("Login");								//Button f�r Login
		anmelden.addActionListener(new ActionListener() {						

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub								//l�st Methode anmelden in Klasse Login aus
				if(Login.anmelden(eingabeName.getText(), eingabePasswort.getPassword())) {	//Text aus Namens- und Passwortfeld wird �bergeben
					LoginGUI.super.dispose();									//Bei erfolgreichem Login wird LoginGUI geschlossen
				}
			}
			
		});
		
		JButton registrieren = new JButton("Registrieren");						//Button f�r Registrierung
		registrieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub								//l�st Methode registrieren der Klasse Login aus
				Login.registrieren(eingabeName.getText(), eingabePasswort.getPassword());	//Text aus Namens- und Passwortfeld wird �bergeben
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
