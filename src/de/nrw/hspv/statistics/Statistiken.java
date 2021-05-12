package de.nrw.hspv.statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;
import de.nrw.hspv.ui.App;

/**
 * Klasse fuer die Aufgabenstatistiken 
 * 
 * @author Janis
 * @version 1.0
 */

public class Statistiken {
	
	
	public static ArrayList<StatistikEintrag> Eintraege = new ArrayList<StatistikEintrag>();
	
	/**
	 * Liest die Statistikeintraege aus der Datei Statistiken aus und speichert sie in der ArrayList Eintraege
	 */
	public static void ladeStatistik() {
		try {
			File statistikFile = new File("Statistiken.txt");									
			if (statistikFile.length() != 0) {													//wenn Datei leer ist, soll nicht ausgelesen werden
				FileInputStream dateiEingabeStrom = new FileInputStream(statistikFile);			
				ObjectInputStream objektEingabeStrom = new ObjectInputStream(dateiEingabeStrom);
				Eintraege = (ArrayList<StatistikEintrag>)objektEingabeStrom.readObject();		//Daten aus Datei werden ArrayList Eintraege zugewiesen
				
				objektEingabeStrom.close();
				dateiEingabeStrom.close();
				
				App.logger.log(Level.INFO, "Statistiken geladen");
			}
		}
		catch(FileNotFoundException e) {
			App.logger.log(Level.SEVERE, "Die Statistikdatei wurde nicht gefunden " + e.getMessage());
		} 
		catch(IOException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Fehler beim Laden der Statistik aufgetreten " + e.getMessage());
		} 
		catch (ClassNotFoundException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Fehler beim Laden der Statistik aufgetreten " + e.getMessage());
		}
		
	}
	
	/**
	 * Legt Statistikeintraege aus der ArrayList Eintraege in der Datei Statistiken ab
	 */
	public static void speicherStatistik() {
		try {
			FileOutputStream dateiAusgabeStrom = new FileOutputStream(new File("Statistiken.txt"));
			ObjectOutputStream objektAusgabeStrom = new ObjectOutputStream(dateiAusgabeStrom);
			
			objektAusgabeStrom.writeObject(Eintraege);										//ArrayList Eintraege wird in Datei geschrieben
			objektAusgabeStrom.close();
			dateiAusgabeStrom.close();
			App.logger.log(Level.INFO, "Statistiken gespeichert");
		}
		catch(NotSerializableException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Fehler beim Speichern der Statistiken aufgetreten " + e.getMessage());
		}
		catch(FileNotFoundException e) {
			App.logger.log(Level.SEVERE, "Die Statistikdatei konnte nicht gefunden werden " + e.getMessage());
		}
		catch(IOException e) {
			App.logger.log(Level.SEVERE, "Es ist ein Fehler beim Speichern der Statistiken aufgetreten " + e.getMessage());
		}
	}
	
	/**
	 * Fuegt der ArrayList Eintraege einen neuen Statistikeintrag hinzu
	 * 
	 * @param user der die Aufgabe bearbeitet hat
	 * @param aufgabe die bearbeitet wurde
	 */
	public static void addEintrag(User user, Exercise aufgabe) {
		Eintraege.add(new StatistikEintrag(user, aufgabe));
		App.logger.log(Level.INFO, "Eintrag zur Statistik hinzugefuegt");
		speicherStatistik();
	}
	
	/**
	 * Ermittelt Anzahl relevanter Statistikeintraege
	 * 
	 * @param username der in Eintraegen enthalten sein soll
	 * @param typ der Aufgaben die in Eintraegen enthalten sein soll
	 * @param geloest Loesungsstatus der Aufgaben die in Eintraegen enthalten sein soll
	 * @return Anzahl der passenden Eintraege als Integer
	 */
	public static int getAnzahl(String username, Aufgabentyp typ, boolean geloest) {
		int anzahl = 0;
		for(StatistikEintrag eintrag : Eintraege) {						//Durchlaufe ArrayList Eintraege
			if(eintrag.aktiverUser.name.equals(username) && eintrag.aufgabentyp == typ && eintrag.geloest == geloest) {	//Ueberpruefe einzelne Statistikeintraege
				anzahl++;
			}
		}
		return anzahl;
	}
	
	/**
	 * Ermittelt Anzahl relevanter Statistikeintraege
	 * 
	 * @param username der in Eintraegen enthalten sein soll
	 * @param typ der Aufgaben die in Eintraegen enthalten sein soll
	 * @return Anzahl der passenden Eintraege als Integer
	 */
	public static int getAnzahl(String username, Aufgabentyp typ) {
		int anzahl = 0;
		for(StatistikEintrag eintrag : Eintraege) {						//Durchlaufe ArrayList Eintraege
			if(eintrag.aktiverUser.name.equals(username) && eintrag.aufgabentyp == typ) {		//Ueberpruefe einzelne Statistikeintraege
				anzahl++;
			}
		}
		return anzahl;
	}
	
}
