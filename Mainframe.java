package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.nrw.hspv.Aufgabentyp;

//import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;


public class Mainframe extends JFrame {
	//lege Objekte an um sie verwaltbar zu machen
	MenueBar menueBar = new MenueBar();
	MainPanel MainPanel = new MainPanel();
	
	
	Mainframe(){
		//Rufe den Konstruktor von JFrame auf
		super("LeapUpLearn - Made-to-Measure LernApp für Verwaltungsinformatik - HSPV NRW");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		setJMenuBar(menueBar);
		setContentPane(MainPanel); 

		setPreferredSize(new Dimension(800,600));
		setSize(getPreferredSize());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) d.getWidth()/2 - this.getWidth()/2, (int) d.getHeight()/2 - this.getHeight()/2);
		setVisible(true);
		themenpanel(); //Menü mit Aufgaben und Statistik anzeigen
	}

	class MainPanel extends JPanel {
		/**
		 * MainPanel: Grundlegendes Panel in dem die Aufgaben/Infos/Menu angezeigt
		 * werden
		 * 
		 * Oben (NORTH) rechts die Aktuelle Bearbeitungszeit Mitte (CENTER) Platz für
		 * die Panels der Aufgaben
		 * 
		 * Links (WEST) Menu mit grundlegenden Programmfunktionen Unten (SOUTH) die
		 * Schaltflächen "Abbrechen" und "Überprüfen"
		 * 
		 */
		public MainPanel() {
			setLayout(new BorderLayout(5, 5));

			// Panel mit Zeitinformationen oben rechts
			// Nachdem Start einer Aufgabe zeigt der Timer die benötigte Zeit
			JPanel TimePanel = new JPanel();
			add(TimePanel, BorderLayout.NORTH);
			TimePanel.setLayout(new BorderLayout(5, 5));
			JLabel lblZeit = new JLabel("Zeit");
			TimePanel.add(lblZeit, BorderLayout.EAST);

			// Timer erstmal zur Demo (ggf. auslagern?)
			final long start = System.currentTimeMillis();
			final long end = start * 60 * 1000;
			final javax.swing.Timer timer = new javax.swing.Timer(100, null);
			timer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					long now = System.currentTimeMillis();
					if (now >= end) {
						lblZeit.setText("");
						timer.stop();
					}
					lblZeit.setText("Bearbeitungszeit: " + (end - now) / 1000 + " Sekunden  ");
				}
			});
			timer.start();

			
		}

	}

	public void themenpanel() {
		// Panel mit Grundlegenden Programmfunktionen (Menu) auf der linken Seite:
		JPanel ThemenPanel = new JPanel();
		add(ThemenPanel, BorderLayout.WEST); // Ausrichtung nach links
		ThemenPanel.setLayout(new GridLayout(0, 1, 0, 0)); // Alle Btn mit GridLayout(damit alle die selbe Größe
															// haben) horizontal anordnen
		// Buttons anlegen und hinzufügen
		JButton btnGrdlIT = new JButton("Grundlagen IT");
		ThemenPanel.add(btnGrdlIT);
		btnGrdlIT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEx("GrdlIT"); //Aufruf der einzelnen Aufgaben zum Kurs Grundlagen IT
				ThemenPanel.setVisible(false);

			}
		});
		JButton btnMathe = new JButton("Mathe");
		ThemenPanel.add(btnMathe);
		btnMathe.setEnabled(true); // nicht implemtiert --> deaktiviert
		btnMathe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEx("Mathe"); //Aufruf der einzelnen Aufgaben zum Kurs Mathe
				ThemenPanel.setVisible(false);
			}
		});
		
		JButton btnStatistik = new JButton("Statistik");
		ThemenPanel.add(btnStatistik);
		btnStatistik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Persönliche Statistik aufrufen
			}
		});

	}
	public void showEx(String Aufgabe){ //Zeigt die Aufgaben des Kurses an
		JPanel ExPanel = new JPanel();
		MainPanel.add(ExPanel, BorderLayout.WEST); 		// Ausrichtung nach links
		ExPanel.setLayout(new GridLayout(0, 1, 0, 0));  // Alle Btn mit GridLayout(damit alle die selbe Größe
		ExPanel.setVisible(true);						// haben) horizontal anordnen
		
		if (Aufgabe == "GrdlIT") {
			// Buttons anlegen und hinzufügen
			JButton btnIPAdressen = new JButton("IP Adressen");
			ExPanel.add(btnIPAdressen);
			JButton btnZahlensystme = new JButton("Zahlensysteme");
			ExPanel.add(btnZahlensystme);
		}
		else if (Aufgabe == "Mathe") {
			JButton btnLogik = new JButton("Logik");
			ExPanel.add(btnLogik);
		}
		
		//Schaltfläche optisch mit JLabel absetzen
		JLabel lblPlatzhalter = new  JLabel();
		ExPanel.add(lblPlatzhalter);
		
		JButton btnZurueck = new JButton("Zurück");
		ExPanel.add(btnZurueck);
		btnZurueck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExPanel.setVisible(false);
				themenpanel();
				}
		});
	}
	class MenueBar extends JMenuBar{
		//Klassenvariablen
		//Einträge in der MenuBar
		JMenu menuDatei, menuKurs, menuInfo;
		
		//Untermenüs --> ebenfalls JMenus: subM = SubMenu
		JMenu subMGrdlIT, subMMathe;
		
		//Elemente der Einträge
		JMenuItem mItmLoad, mItmSave, mItmAufgIPAdress, mItmScheduling, mItmLogik, mItmInfo;
		
	
		//quasi Methode initialize() im originalcode
		MenueBar(){
			super();	//rufe den Konstrukter von JMenuBar auf
			
		//Steuerelemente anlegen und Funktionen implementieren
			//Menüs / Reiter
			menuDatei = new JMenu("Datei");
			menuKurs = new JMenu("Kurs");
			menuInfo = new JMenu("Info");
			
			//Menü-Einträge --- mItm = menuItem
			mItmLoad = new JMenuItem("Laden");
			mItmSave = new JMenuItem("Speichern");
			
			//der Menü-Punkt Kurse enthält die Kurse als Untermenus "subM..."
			subMGrdlIT = new JMenu("Grundlagen IT");
				//Aufgaben zum Kurs Grundlagen IT anlegen:
					mItmAufgIPAdress = new JMenuItem("IP v4 / Subnetting"); 
					mItmAufgIPAdress.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
					mItmScheduling = new JMenuItem("Scheduling");
			
			subMMathe = new JMenu("Mathematik");
				//Aufgaben zum Kurs Mathe anlegen:
					mItmLogik = new JMenuItem("Logik");
			
		
			mItmInfo = new JMenuItem("Über...");
			mItmInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InfoAbout info = new InfoAbout();
					info.setVisible(true);
				}
			});
			
		//Menu aufbauen
			//Menüs der Menü-Leiste hinzufügen
			add(menuDatei);
			add(menuKurs);
			add(menuInfo);
			
			//Einträge zum Datei-Menü hinzufügen
			menuDatei.add(mItmLoad);
			menuDatei.add(mItmSave);
			
			//Einträge zum Kurs-Menü hinzufügen
			menuKurs.add(subMGrdlIT);
				subMGrdlIT.add(mItmAufgIPAdress);
				subMGrdlIT.add(mItmScheduling);
				mItmScheduling.setEnabled(false); //nicht implementiert
			menuKurs.add(subMMathe);
				subMMathe.add(mItmLogik);
				mItmLogik.setEnabled(false); //nicht implementiert
			menuInfo.add(mItmInfo);
		}	
	}
}