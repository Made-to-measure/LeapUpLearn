package de.nrw.hspv.ui;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import de.nrw.hspv.login.LoginGUI;

public class App {
	/**
	 * Einstiegspunkt um die App zu starten
	 * 
	 * @author Janis, Jannik
	 * @version 1.0
	 */
	
	//initialisiere logger
	public static final Logger logger = Logger.getLogger(App.class.getName());
	
	//Systemspezifikationen (inspieriert von Marco Wilhems)
	private static String os = System.getProperty("os.name") + "Version " + System.getProperty("os.version");
	private static String screenResulution = Toolkit.getDefaultToolkit().getScreenSize().getWidth() + "x" + Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static void main(String[] args) {
		generateLogFile();
		setLogLevel(Level.INFO);
		LoginGUI loginGUI = new LoginGUI();		//erzeuge LoginGUI als Start
//		Mainframe mainframe = new Mainframe(); //erzeuge den Hauptscreen quasi Appstart
	}
	
	
	/**
	 * generateLogFile generiert die LogFile mit einigen Einzelheiten zum System
	 * 
	 * @autor Jannik
	 * 
	 */
	public static void generateLogFile() {
		// TODO Auto-generated method stub
		
		try {
			logger.addHandler(new FileHandler("log.txt"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Start");
		logger.log(Level.INFO, "Betriebssystem: " + os + "\nBildschirmauflösung: " + screenResulution);
		
	}
	
	/**
	 * setLogLevel setzt das Level des Logs indem der Parameter Level übergeben wird
	 * @param level
	 */
	public static void setLogLevel(Level level) {
		logger.setLevel(level);
		logger.log(Level.INFO, "Loglevel gesetzt auf: " + level);
	}
	
	
}
