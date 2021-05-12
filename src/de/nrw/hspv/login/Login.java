package de.nrw.hspv.login;

import java.util.HashMap;
import java.util.logging.Level;

import de.nrw.hspv.ui.App;
import de.nrw.hspv.ui.HinweisFenster;
import de.nrw.hspv.ui.Mainframe;
import de.nrw.hspv.statistics.Statistiken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Klasse mit Funktionen zur Anmeldung oder Registrierung
 * @author Janis
 * @version 1.0
 */
public class Login {
	
	private static HashMap<String, String> loginDaten = new HashMap<String, String>();		//HashMap soll Nutzernamen und Passwörter beinhalten
	public static User aktiverUser = new User();
	
	/**
	 * Laedt die Daten aus der Login-Datei in die HashMap loginDaten
	 */
	public static void ladeDaten() {														//Daten aus Datei werden geladen
		try {
			File loginFile = new File("LoginDaten.txt");									
			if (loginFile.length() != 0) {													//wenn Datei leer ist, soll nicht ausgelesen werden
				FileInputStream dateiEingabeStrom = new FileInputStream(loginFile);			
				ObjectInputStream objektEingabeStrom = new ObjectInputStream(dateiEingabeStrom);
				loginDaten = (HashMap<String,String>)objektEingabeStrom.readObject();		//Daten aus Datei werden HashMap loginDaten zugewiesen
				
				objektEingabeStrom.close();
				dateiEingabeStrom.close();
			}
			App.logger.log(Level.INFO, "Logindaten geladen");
		}
		catch(FileNotFoundException e) {
			App.logger.log(Level.SEVERE, "Logindatei nicht gefunden: " + e.getMessage());
		} 
		catch(IOException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Problem beim Laden der Logindatei aufgetreten " + e.getMessage());
		} 
		catch (ClassNotFoundException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Problem beim Laden der Logindatei aufgetreten " + e.getMessage());
		}
		
	}
	
	/**
	 * speichert die Daten aus der Hashmap loginDaten in der Login-Datei
	 */
	public static void speichereDaten() {													//Daten sollen aus HashMap in Datei gespeichert werden
		try {
			FileOutputStream dateiAusgabeStrom = new FileOutputStream(new File("LoginDaten.txt"));
			ObjectOutputStream objektAusgabeStrom = new ObjectOutputStream(dateiAusgabeStrom);
			
			objektAusgabeStrom.writeObject(loginDaten);										//HashMap loginDaten werden in Datei geschrieben
			objektAusgabeStrom.close();
			dateiAusgabeStrom.close();
			
			App.logger.log(Level.INFO, "Logindaten gespeichert");
		}
		catch(FileNotFoundException e) {
			App.logger.log(Level.SEVERE, "Logindatei nicht gefunden" + e.getMessage());
		}
		catch(IOException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Problem beim Speichern der Logindaten aufgetreten " + e.getMessage());
		}
	}
	
	/**
	 * Fuehrt die Anmeldung durch
	 * @param benutzer der angemeldet werden soll
	 * @param passwort das der Nutzer zur Anmeldung angegeben hat
	 * @return boolean, ob Anmeldung erfolgreich
	 */
	public static boolean anmelden(String benutzer, char[] passwort) {						//Mehtode um Anmeldung durchzuführen
		ladeDaten();																		//Daten aus Datei laden
		if(benutzer.isEmpty()) {															//Benutzername ist leer
			new HinweisFenster("Kein Benutzername eingetragen");							//Hinweisfenster wird erzeugt
			return false;
		}
		else if(loginDaten.containsKey(benutzer)) {											//Benutzername ist in LoginDaten vorhanden
			if(loginDaten.get(benutzer).compareTo(String.valueOf(passwort)) == 0) {			//Login erfolgreich
				App.logger.log(Level.INFO, "Login des Nutzers " + benutzer + " erfolgreich");
				Mainframe mainframe = new Mainframe();										//Start des Hauptprogramms
				aktiverUser.name = benutzer;
				Statistiken.ladeStatistik();
				return true;
			}
			else {																			//Passwort nicht richtig
				new HinweisFenster("falsches Passwort");
				return false;
			}
		}
		else {																				//Nutzername nicht in LoginDatei vorhanden
			new HinweisFenster("Nutzername nicht vorhanden");
			return false;
		}
	}
	
	/**
	 * Fuehrt die Registrierung durch
	 * @param benutzer der registriert werden soll
	 * @param passwort das der Nutzer zur Registrierung angegeben hat
	 * @return boolean, ob Registrierung erfolgreich
	 */
	public static boolean registrieren(String benutzer, char[] passwort) {					//Methode um Registrierung durchzuführen
		if(benutzer.isEmpty()) {															//Benutzername ist leer
			new HinweisFenster("Kein Benutzername eingetragen");
			return false;
		}
		if(String.valueOf(passwort).isEmpty()) {											//Passwort ist leer
			new HinweisFenster("Kein Passwort eingetragen");
			return false;
		}
		ladeDaten();																		//Daten werden geladen
		if(loginDaten.containsKey(benutzer)) {												//Benutzername schon in LoginDatei vorhanden
			new HinweisFenster("Der Name ist bereits vergeben");
			return false;
		}
		else {																				//Benutzer wird registriert
			loginDaten.put(benutzer, String.valueOf(passwort));
			App.logger.log(Level.INFO, "Registrierung des Benutzers " + benutzer + " erfolgreich");
			speichereDaten();
			new HinweisFenster("Nutzer " + benutzer + " erfolgreich registriert");
			return true;
		}
	}
}
