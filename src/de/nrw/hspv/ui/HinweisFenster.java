package de.nrw.hspv.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HinweisFenster extends JFrame {					//Hinweisfenster f�r Meldungen an Nutzer
	
	public HinweisFenster(String text) {								//im Konstruktor wird Inhalt der Meldung �bergeben
		super("Achtung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		
		JPanel panelOben = new JPanel();						//panelOben f�r Inhalt der Meldung
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel hinweis = new JLabel(text);						//Label enth�lt Inhalt der Meldung
		panelOben.add(hinweis);
		add(panelOben, BorderLayout.CENTER);
		
		JPanel panelUnten = new JPanel();						//panelUnten f�r Button
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HinweisFenster.super.dispose();					//Button schlie�t Hinweisfenster
			}
			
		});
		panelUnten.add(ok);
		add(panelUnten, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
		//Fenster wird in der Mitte des Bildschirms dargestellt
	}

}