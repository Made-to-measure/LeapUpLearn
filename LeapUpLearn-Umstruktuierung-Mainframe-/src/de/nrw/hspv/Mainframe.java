package de.nrw.hspv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Mainframe extends JFrame {
/**
 * 	
 * @author Jannik: Christian hat sich die Grundstruktur f�r das Men� ausgedacht und die haupts�chliche gedankliche Arbeit hereingesteckt.
 *
 */
	//lege Objekte an um sie verwaltbar zu machen
	MenueBar menueBar = new MenueBar();
	
	Mainframe(){
		//Rufe den Konstruktor von JFrame auf
		super("LeapUpLearn");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		setJMenuBar(menueBar);
		
		setPreferredSize(new Dimension(450,300));
		setSize(getPreferredSize());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) d.getWidth()/2 - this.getWidth()/2, (int) d.getHeight()/2 - this.getHeight()/2);
		setVisible(true);
	}
	
	class MenueBar extends JMenuBar{
		//Klassenvariablen
		JMenu menuDatei, menuKurs, menuInfo;
		JMenuItem mItmLoad, mItmSave, mItmGrundlagenIT, mItmMath, mItmInfo;
		
		
		//quasi Methode initialize() im originalcode
		MenueBar(){
			super();	//rufe den Konstrukter von JMenuBar auf
			
			
			//Men�s / Reiter
			menuDatei = new JMenu("Datei");
			menuKurs = new JMenu("Kurs");
			menuInfo = new JMenu("Info");
			
			//Men�-Eintr�ge --- mItm = menuItem
			mItmLoad = new JMenuItem("Laden");
			mItmSave = new JMenuItem("Speichern");
			
			mItmGrundlagenIT = new JMenuItem("Grundlagen IT");
			mItmMath = new JMenuItem("Mathematik");
			
			mItmInfo = new JMenuItem("�ber...");
			mItmInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InfoAbout info = new InfoAbout();
					info.setVisible(true);
				}
			});
			
			//Men�s der Men�-Leiste hinzuf�gen
			add(menuDatei);
			add(menuKurs);
			add(menuInfo);
			
			//Eintr�ge zum Datei-Men� hinzuf�gen
			menuDatei.add(mItmLoad);
			menuDatei.add(mItmSave);
			
			//Eintr�ge zum Kurs-Men� hinzuf�gen
			//Nicht implementierte Module deaktivieren ;)
			menuKurs.add(mItmGrundlagenIT);
			menuKurs.add(mItmMath);
			menuInfo.add(mItmInfo);
		}
	}
	
//	public static void main (String[] args) {
//		new Mainframe();
//	}
}
