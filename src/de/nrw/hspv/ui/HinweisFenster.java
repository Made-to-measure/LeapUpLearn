package de.nrw.hspv.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.awt.event.ActionEvent;

/**
 * Hinweisfesnter fuer schnelle Information an Benutzer
 * @author Janis
 * @version 1.0
 */
public class HinweisFenster extends JFrame {					//Hinweisfenster für Meldungen an Nutzer
	/**
	 * Erzeugt ein Fenster (JFrame) mit Hinweisen im LUL Design
	 * @param Hinweistext String 
	 */
	public HinweisFenster(String text) {								//im Konstruktor wird Inhalt der Meldung uebergeben
		super("Achtung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));	//Icon fuer HinweisFenster wird gesetzt
		setLayout(new BorderLayout());							//BorderLayout zur Aufteilung
		
		JPanel panelOben = new JPanel();						//panelOben für Inhalt der Meldung
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel hinweis = new JLabel(text);						//Label enthält Inhalt der Meldung
		panelOben.add(hinweis);
		add(panelOben, BorderLayout.CENTER);					//Panel mit Meldung soll im Center des BorderLayouts liegen
		
		JPanel panelUnten = new JPanel();						//panelUnten für Button
		JButton ok = new JButton("OK");							//OKButton wird erzeugt
		ok.addActionListener(new ActionListener() {				//Action Listener um Fenster mit Button zu schliessen

			@Override
			public void actionPerformed(ActionEvent e) {
				HinweisFenster.super.dispose();					//Button schließt Hinweisfenster
			}
			
		});
		panelUnten.add(ok);										//OKButton wird panelUnten hinzugefuegt
		add(panelUnten, BorderLayout.SOUTH);					//Panel fuer Buttons soll im BorderLayout unten erscheinen
		
		App.logger.log(Level.INFO, "HinweisFenster erzeugt");
		
		setVisible(true);
		pack();
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
		//Fenster wird in der Mitte des Bildschirms dargestellt
	}

}
