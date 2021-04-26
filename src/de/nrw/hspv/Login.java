package de.nrw.hspv;

import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Login {

	private static HashMap<String, String> loginDaten = new HashMap<String, String>();
	
	public static void ladeDaten() {
		try {
			File loginFile = new File("LoginDaten.txt");
			if (loginFile.length() != 0) {
				FileInputStream dateiEingabeStrom = new FileInputStream(loginFile);
				ObjectInputStream objektEingabeStrom = new ObjectInputStream(dateiEingabeStrom);
				loginDaten = (HashMap<String,String>)objektEingabeStrom.readObject();
				
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
	
	public static void speichereDaten() {
		try {
			FileOutputStream dateiAusgabeStrom = new FileOutputStream(new File("LoginDaten.txt"));
			ObjectOutputStream objektAusgabeStrom = new ObjectOutputStream(dateiAusgabeStrom);
			
			objektAusgabeStrom.writeObject(loginDaten);
			objektAusgabeStrom.close();
			dateiAusgabeStrom.close();
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	public static boolean anmelden(String benutzer, char[] passwort) {
		ladeDaten();
		if(benutzer.isEmpty()) {
			new HinweisFenster("Kein Benutzername eingetragen");
			return false;
		}
		else if(loginDaten.containsKey(benutzer)) {		//Benutzername ist in LoginDaten vorhanden
			if(loginDaten.get(benutzer).compareTo(String.valueOf(passwort)) == 0) {			//Login erfolgreich
				//LoginGUI muss noch geschlossen werden
				Mainframe mainframe = new Mainframe();				//Start des Hauptprogramms
				return true;
			}
			else {									//Passwort nicht richtig
				new HinweisFenster("falsches Passwort");
				return false;
			}
		}
		else {										//Nutzername nicht in LoginDatei vorhanden
			new HinweisFenster("Nutzername nicht vorhanden");
			return false;
		}
	}
	public static boolean registrieren(String benutzer, char[] passwort) {
		if(benutzer.isEmpty()) {
			new HinweisFenster("Kein Benutzername eingetragen");
			return false;
		}
		if(String.valueOf(passwort).isEmpty()) {
			new HinweisFenster("Kein Passwort eingetragen");
			return false;
		}
		ladeDaten();
		if(loginDaten.containsKey(benutzer)) {		//Benutzername schon in LoginDatei vorhanden
			new HinweisFenster("Der Name ist bereits vergeben");
			return false;
		}
		else {										//Benutzer wird registriert
			loginDaten.put(benutzer, String.valueOf(passwort));
			speichereDaten();
			new HinweisFenster("Nutzer " + benutzer + " erfolgreich registriert");
			return true;
		}
	}
}
