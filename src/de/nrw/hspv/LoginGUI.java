package de.nrw.hspv;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame{
	
	JTextField eingabeName;
	JPasswordField eingabePasswort;
	
	LoginGUI() {
		super("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		
		JPanel panelOben = new JPanel();
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		panelOben.setLayout(new GridLayout(2,2,0,0));
		
		
		JLabel labelName = new JLabel("Nutzername");
		panelOben.add(labelName);
		eingabeName = new JTextField();
		panelOben.add(eingabeName);
				
		JLabel labelPasswort = new JLabel("Passwort");
		panelOben.add(labelPasswort);
		eingabePasswort = new JPasswordField();
		panelOben.add(eingabePasswort);		
		
		add(panelOben, BorderLayout.CENTER);
		
		JPanel panelUnten = new JPanel();
		
		JButton anmelden = new JButton("Login");
		anmelden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Login.anmelden(eingabeName.getText(), eingabePasswort.getPassword())) {
					LoginGUI.super.dispose();
				}
			}
			
		});
		
		JButton registrieren = new JButton("Registrieren");
		registrieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login.registrieren(eingabeName.getText(), eingabePasswort.getPassword());
			}
			
		});
		panelUnten.add(anmelden);
		panelUnten.add(registrieren);
		add(panelUnten, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
	}

	public static void main(String[] args) {
		new LoginGUI();
	}
	
}
