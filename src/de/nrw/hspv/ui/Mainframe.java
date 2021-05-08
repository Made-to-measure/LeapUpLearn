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

//import de.nrw.hspv.Aufgabentyp;

//import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;


public class Mainframe extends JFrame {
	/**
	 * Klasse für die grafische Benutzeroberfläche 
	 * 
	 * 
	 * @author Christian
	 * @version 1.0
	 * 
	 */
	
	//lege Objekte an um sie verwaltbar zu machen
	MenueBar menueBar = new MenueBar();
	MainPanel MainPanel;
	BorderLayout mainPanelLayout;

	
	
	public Mainframe(){
		//Rufe den Konstruktor von JFrame auf
		super("LeapUpLearn - Made-to-Measure LernApp für Verwaltungsinformatik - HSPV NRW");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		setJMenuBar(menueBar);
		setContentPane(MainPanel = new MainPanel()); 

		setMinimumSize(new Dimension(800,600));
		setSize(getPreferredSize());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) d.getWidth()/2 - this.getWidth()/2, (int) d.getHeight()/2 - this.getHeight()/2);
		
		themenpanel(); //Menü mit Aufgaben und Statistik anzeigen
		//pack();
		setVisible(true);
		
	}

	class MainPanel extends JPanel {
		/**
		 * Grundlegendes Panel in dem die Aufgaben/Infos/Menu angezeigt
		 * werden <br><br>
		 * 
		 * - Oben (NORTH) rechts die Aktuelle Bearbeitungszeit Mitte (CENTER) Platz für
		 * die Panels der Aufgaben<br>
		 * 
		 * - Links (WEST) Menu mit grundlegenden Programmfunktionen <br>
		 * - Unten (SOUTH) die
		 * Schaltflächen "Abbrechen" und "Überprüfen"<br>
		 * 
		 * @author Christian
		 * @version 1.0
		 * 
		 * 
		 */
		public MainPanel() {
			setLayout(mainPanelLayout = new BorderLayout(5,5));
			
		}

	}

	public void themenpanel() {
		/**
		 * Methode legt ein Panel mit grundlegenden Programmfunktionen (Menu) 
		 * auf der linken Seite:
		 * 
		 */
		
		JPanel ThemenPanel = new JPanel();
		MainPanel.add(ThemenPanel, BorderLayout.WEST); // Ausrichtung nach links
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
				Stopwatch stopwatch = new Stopwatch();
				
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
				removeCenter();
			}
		});

	}
	public void showEx(String Aufgabe){ 
		/**
		 * Zeigt die Aufgaben des Kurses an
		 * 
		 */ 
		JPanel ExPanel = new JPanel();
		MainPanel.add(ExPanel, BorderLayout.WEST); 		// Ausrichtung nach links
		ExPanel.setLayout(new GridLayout(0, 1, 0, 0));  // Alle Btn mit GridLayout(damit alle die selbe Größe
		ExPanel.setVisible(true);						// haben) horizontal anordnen
		
		if (Aufgabe == "GrdlIT") {
			// Buttons für Kurs GrdlIT anlegen und hinzufügen
			// IPAdressen Btn anlegen Aufgabe aufrufen
			JButton btnIPAdressen = new JButton("IP Adressen");
			btnIPAdressen.addActionListener(new ActionListener() { // IPAdressen Aufgabe aufrufen
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCenter(); 
					MainPanel.add(new UI_IPExercise(),BorderLayout.CENTER);
					MainPanel.revalidate();
					MainPanel.repaint();	
				}
			});
			ExPanel.add(btnIPAdressen);
			
			JButton btnZahlensysteme = new JButton("Zahlensysteme");
			btnZahlensysteme.addActionListener(new ActionListener () {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCenter();
					MainPanel.add(new UIZahlensysteme(), BorderLayout.CENTER);
					MainPanel.revalidate();
					MainPanel.repaint();					
				}
				
			});
			ExPanel.add(btnZahlensysteme);
		}
		else if (Aufgabe == "Mathe") {
			JButton btnLogik = new JButton("Logik");
			ExPanel.add(btnLogik);
		}
		
		//Schaltfläche optisch mit JLabel absetzen
		JLabel lblPlatzhalter = new  JLabel();
		ExPanel.add(lblPlatzhalter);
		
		//Zurück-Button um zu den Kursen zurückzukehren
		JButton btnZurueck = new JButton("Zurück");
		ExPanel.add(btnZurueck);
		btnZurueck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExPanel.setVisible(false);
					themenpanel();
					//removeCenter();
			}
		});
	}
	
	public void removeCenter() {
		/**
		 * Methode entfernt den Inhalt aus dem "Center" 
		 * des MainPanel
		 * 
		 * @author Jannik
		 * @version 1.0
		 * 
		 */
		if(mainPanelLayout.getLayoutComponent(BorderLayout.CENTER) != null){
		MainPanel.remove(mainPanelLayout.getLayoutComponent(BorderLayout.CENTER));
		MainPanel.revalidate();
		MainPanel.repaint();
		}
		
	}
	class MenueBar extends JMenuBar{
		/**
		 * Klasse für das Programmmenü, Untermenüs, MenuItem
		 * 
		 * @author Christian
		 * @version 1.0
		 */
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
						@Override
						public void actionPerformed(ActionEvent e) {
							removeCenter(); 		//
							MainPanel.add(new UI_IPExercise(),BorderLayout.CENTER);
							MainPanel.revalidate();  //Methoden damit die Aufgabe nach dem Laden
							MainPanel.repaint();	 //auch angezeigt wird
						}
					});
					mItmScheduling = new JMenuItem("Scheduling");
			
			subMMathe = new JMenu("Mathematik");
				//Aufgaben zum Kurs Mathe anlegen:
					mItmLogik = new JMenuItem("Logik");
			
				//Menüpunkt zu den Credits
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