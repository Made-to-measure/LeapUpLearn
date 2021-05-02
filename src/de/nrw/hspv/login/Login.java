package de.nrw.hspv.login;

import java.util.HashMap;

import de.nrw.hspv.ui.HinweisFenster;
import de.nrw.hspv.ui.Mainframe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Login {

	private static HashMap<String, String> loginDaten = new HashMap<String, String>();		//HashMap soll Nutzernamen und Passwörter beinhalten
	
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
		}
		catch(FileNotFoundException e) {
			
		} 
		catch(IOException e) {
			
		} 
		catch (ClassNotFoundException e) {
			
		}
	}
	
	public static void speichereDaten() {													//Daten sollen aus HashMap in Datei gespeichert werden
		try {
			FileOutputStream dateiAusgabeStrom = new FileOutputStream(new File("LoginDaten.txt"));
			ObjectOutputStream objektAusgabeStrom = new ObjectOutputStream(dateiAusgabeStrom);
			
			objektAusgabeStrom.writeObject(loginDaten);										//HashMap loginDaten werden in Datei geschrieben
			objektAusgabeStrom.close();
			dateiAusgabeStrom.close();
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	public static boolean anmelden(String benutzer, char[] passwort) {						//Mehtode um Anmeldung durchzuführen
		ladeDaten();																		//Daten aus Datei laden
		if(benutzer.isEmpty()) {															//Benutzername ist leer
			new HinweisFenster("Kein Benutzername eingetragen");							//Hinweisfenster wird erzeugt
			return false;
		}
		else if(loginDaten.containsKey(benutzer)) {											//Benutzername ist in LoginDaten vorhanden
			if(loginDaten.get(benutzer).compareTo(String.valueOf(passwort)) == 0) {			//Login erfolgreich
				Mainframe mainframe = new Mainframe();										//Start des Hauptprogramms
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
			speichereDaten();
			new HinweisFenster("Nutzer " + benutzer + " erfolgreich registriert");
			return true;
		}
	}
}
