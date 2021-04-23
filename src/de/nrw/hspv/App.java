package de.nrw.hspv;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class App {

	private JFrame frame;

	/**
	 * Die App starten
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * App erzeugen
	 */
	public App() {
		initialize();
	}

	/**
	 * Inhalte des Fensters initialisieren
	 */
	private void initialize() {
		//Menü-Objekte anlegen
		JMenuBar menuBar;
		JMenu menuDatei, menuKurs, menuInfo;
		JMenuItem mItmLoad, mItmSave, mItmGrundlagenIT, mItmMath, mItmInfo;
		
		//Menü-Leiste ansich
		menuBar = new JMenuBar();
		//Menüs
		menuDatei = new JMenu("Datei");
		menuKurs = new JMenu("Kurs");
		menuInfo = new JMenu("Info");
		//Menü-Einträge --- mItm = menuItem
		mItmLoad = new JMenuItem("Laden");
		mItmSave = new JMenuItem("Speichern");
		
		mItmGrundlagenIT = new JMenuItem("Grundlagen IT");
		mItmMath = new JMenuItem("Mathematik");
		
		mItmInfo = new JMenuItem("Über...");
		mItmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoAbout info = new InfoAbout();
				info.setVisible(true);
			}
		});
		
		
		//Menüs der Menü-Leiste hinzufügen
		menuBar.add(menuDatei);
		menuBar.add(menuKurs);
		menuBar.add(menuInfo);
		
		//Einträge zum Datei-Menü hinzufügen
		menuDatei.add(mItmLoad);
		menuDatei.add(mItmSave);
		
		//Einträge zum Kurs-Menü hinzufügen
		//Nicht implementierte Module deaktivieren ;)
		menuKurs.add(mItmGrundlagenIT);
		mItmGrundlagenIT.setEnabled(true);
		
		menuKurs.add(mItmMath);
		mItmMath.setEnabled(false);
		
		menuInfo.add(mItmInfo);
		
		

		frame = new JFrame();
		//Programm-Icon setzen
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		
		frame.setTitle("LeapUpLearn");
		//Menü hinzufügen
		frame.setJMenuBar(menuBar);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
