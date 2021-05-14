package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Klasse fuer die grafische Benutzeroberflaeche 
 * 
 * 
 * @author Christian
 * @version 1.0
 * 
 */
public class Mainframe extends JFrame {
	//lege Objekte an um sie verwaltbar zu machen
	MenueBar menueBar = new MenueBar();
	MainPanel MainPanel;
	BorderLayout mainPanelLayout;

	public Mainframe(){
		//Rufe den Konstruktor von JFrame auf
		super("LeapUpLearn - Made-to-Measure LernApp f\u00FCr Verwaltungsinformatik - HSPV NRW");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		setIconImage(icon);	//wirft anscheinend keine Fehler trotz MacOS

		setLayout(new BorderLayout());
		setJMenuBar(menueBar);
		setContentPane(MainPanel = new MainPanel()); 

		setMinimumSize(new Dimension(800,600));
		setSize(getPreferredSize());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) d.getWidth()/2 - this.getWidth()/2, (int) d.getHeight()/2 - this.getHeight()/2);
		
		themenpanel(); //Menue mit Aufgaben und Statistik anzeigen
		//pack();
		App.logger.log(Level.INFO, "Mainframe erzeugt");
		setVisible(true);
	}

	class MainPanel extends JPanel {
		/**
		 * Grundlegendes Panel in dem die Aufgaben/Infos/Menu angezeigt
		 * werden <br><br>
		 * 

		 * - Oben (NORTH) rechts die Aktuelle Bearbeitungszeit Mitte (CENTER) Platz fuer
		 * die Panels der Aufgaben<br>
		 * 
		 * - Links (WEST) Menu mit grundlegenden Programmfunktionen <br>
		 * - Unten (SOUTH) die
		 * Schaltflaechen "Abbrechen" und "ueberpruefen"<br>
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
		 * Methode legt ein Panel mit grundlegenden Programmfunktionen (Kurse, Menu, Statistik) 
		 * auf der linken Seite:
		 * 
		 */
		
		JPanel ThemenPanel = new JPanel();
		MainPanel.add(ThemenPanel, BorderLayout.WEST); // Ausrichtung nach links
		ThemenPanel.setLayout(new GridLayout(0, 1, 0, 0)); // Alle Btn mit GridLayout(damit alle die selbe Groesse
															// haben) horizontal anordnen
		// Buttons anlegen und hinzufuegen
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
				MainPanel.add(new UIStatistics(), BorderLayout.CENTER);
				MainPanel.revalidate();
				MainPanel.repaint();
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
		ExPanel.setLayout(new GridLayout(0, 1, 0, 0));  // Alle Btn mit GridLayout(damit alle die selbe Groesse
		ExPanel.setVisible(true);						// haben) horizontal anordnen
		
		if (Aufgabe == "GrdlIT") {
			// Buttons fuer Kurs GrdlIT anlegen und hinzufuegen
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
			btnZahlensysteme.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					removeCenter();
					MainPanel.add(new UIZahlensysteme(), BorderLayout.CENTER);
					MainPanel.revalidate();
					MainPanel.repaint();
					
				}
				
			});
			ExPanel.add(btnZahlensysteme);
			
			JButton btnCPUTakt = new JButton("Zugriffszeiten");
			btnCPUTakt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					removeCenter();
					MainPanel.add(new UI_CPUTakt(), BorderLayout.CENTER);
					MainPanel.revalidate();
					MainPanel.repaint();
					
				}
				
			});
			ExPanel.add(btnCPUTakt);
		}
		else if (Aufgabe == "Mathe") {
			JButton btnLogik = new JButton("Logik");
			ExPanel.add(btnLogik);
		}
		
		//Schaltflaeche optisch mit JLabel absetzen
		JLabel lblPlatzhalter = new  JLabel();
		ExPanel.add(lblPlatzhalter);
		
    //Zurueck-Button um zu den Kursen zurueckzukehren
		JButton btnZurueck = new JButton("Zur\u00fcck");

		
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
		 * Klasse fuer das Programmmenue, Untermenues, MenuItem
		 * 
		 * @author Christian
		 * @version 1.0
		 */
		//Klassenvariablen
		//Eintraege in der MenuBar
		JMenu menuDatei, menuKurs, menuInfo;
		
		//Untermenues --> ebenfalls JMenus: subM = SubMenu
		JMenu subMGrdlIT, subMMathe;
		
		//Elemente der Eintraege
		JMenuItem mItmLoad, mItmSave, mItmAufgIPAdress, mItmScheduling, mItmZahlensysteme, mItmCPUTakt,
		mItmLogik, mItmInfo, mItmOptions;
		
	
		MenueBar(){
			super();	//rufe den Konstrukter von JMenuBar auf
			
		//Steuerelemente anlegen und Funktionen implementieren
			//Menues / Reiter
			//menuDatei = new JMenu("Datei");
			menuKurs = new JMenu("Kurs");
			menuInfo = new JMenu("Info");
			
			//Menue-Eintraege --- mItm = menuItem
			//mItmLoad = new JMenuItem("Laden");
			//mItmSave = new JMenuItem("Speichern");
			
			//der Menue-Punkt Kurse enthaelt die Kurse als Untermenus "subM..."
			subMGrdlIT = new JMenu("Grundlagen IT");
				//Aufgaben zum Kurs Grundlagen IT anlegen und Funktion zum Aufruf implementieren:
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
					mItmZahlensysteme = new JMenuItem("Zahlensysteme"); 
					mItmZahlensysteme.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							removeCenter(); 		//
							MainPanel.add(new UIZahlensysteme(),BorderLayout.CENTER);
							MainPanel.revalidate();  //Methoden damit die Aufgabe nach dem Laden
							MainPanel.repaint();	 //auch angezeigt wird
						}
					});
					mItmCPUTakt = new JMenuItem("Zugriffszeiten"); 
					mItmCPUTakt.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							removeCenter(); 		//
							MainPanel.add(new UI_CPUTakt(),BorderLayout.CENTER);
							MainPanel.revalidate();  //Methoden damit die Aufgabe nach dem Laden
							MainPanel.repaint();	 //auch angezeigt wird
						}
					});
					//mItmScheduling = new JMenuItem("Scheduling");
			
			subMMathe = new JMenu("Mathematik");
				//Aufgaben zum Kurs Mathe anlegen:
			mItmLogik = new JMenuItem("Logik");
		

			mItmOptions = new JMenuItem("Optionen");
			mItmOptions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeCenter();
					OptionPanel Options = new OptionPanel();
					MainPanel.add(Options, BorderLayout.CENTER);
					Options.setVisible(true);
					//optionpanel();
					
					MainPanel.revalidate();  //Methoden damit die Optionen
					MainPanel.repaint();	 //auch angezeigt werden
				}
			});
			mItmInfo = new JMenuItem("\u00dcber...");

			mItmInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InfoAbout info = new InfoAbout();
					info.setVisible(true);
				}
			});
		//Menu aufbauen
			//Menues der Menue-Leiste hinzufuegen
			//add(menuDatei);
			add(menuKurs);
			add(menuInfo);
			
			//Eintruege zum Datei-Menue hinzufuegen
			//menuDatei.add(mItmLoad);
			//menuDatei.add(mItmSave);
			
			//Eintruege zum Kurs-Menue hinzufuegen
			menuKurs.add(subMGrdlIT);
				subMGrdlIT.add(mItmAufgIPAdress);
				subMGrdlIT.add(mItmZahlensysteme);
				subMGrdlIT.add(mItmCPUTakt);
				//subMGrdlIT.add(mItmScheduling);
				//mItmScheduling.setEnabled(false); //nicht implementiert
			menuKurs.add(subMMathe);
				subMMathe.add(mItmLogik);
				mItmLogik.setEnabled(false); //nicht implementiert
			menuInfo.add(mItmOptions);
			menuInfo.add(mItmInfo);

		}	
	}
}