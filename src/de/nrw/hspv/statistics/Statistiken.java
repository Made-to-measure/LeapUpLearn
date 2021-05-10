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

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;

/**
 * Klasse für die Aufgabenstatistiken 
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
			}
		}
		catch(FileNotFoundException e) {
			
		} 
		catch(IOException e) {
			
		} 
		catch (ClassNotFoundException e) {
			
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
		}
		catch(NotSerializableException e) {
			
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	/**
	 * Fuegt der ArrayList Eintraege einen neuen Statistikeintrag hinzu
	 * 
	 * @param user
	 * @param aufgabe
	 */
	public static void addEintrag(User user, Exercise aufgabe) {
		Eintraege.add(new StatistikEintrag(user, aufgabe));
		speicherStatistik();
	}
	
	/**
	 * Gibt die Anzahl an Aufgaben zurueck, bei denen User, Aufgabentyp und Loesungsstatus uebereinstimmen
	 * 
	 * @param username
	 * @param typ
	 * @param geloest
	 * @return
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
	 * Gibt die Anzahl an Aufgaben zurueck, bei den User und Aufgabentyp uebereinstimmen
	 * 
	 * @param username
	 * @param typ
	 * @return
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
	
	/**
	 * vielleicht kann man auch soetwas wie overallStats schon direkt als Attribut abspeichern, sobald ein Neuer Eintrag hinzukommt
	 * Ist vielleicht sinnvoller bzw. ressourcenschonender als bei bestimmten Berechnungen jedesmal den gesamten Datensatz durch zugehen
	 * Beispiel:
	 * long geloesteAufgaben;
	 * long gesamteAufgaben;
	 * wieder optional: long bearbeitungszeit; (in Minuten so als Bonus um mittzuteilen wie viel man schon gelernt hat)
	 *  -(Jannik)
	 */
	
	
	
	

}
