package de.nrw.hspv.statistics;

import java.util.ArrayList;
import java.util.Date;

import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.ui.User;

public class Statistiken {
	/**
	 * Klasse f�r die Bearbeitungsstatistiken 
	 * 
	 * @author Janis, Jannik
	 * @version 1.0
	 */
	public User User;
	public Date Datum;
	public ArrayList<StatistikEintrag> Aufgaben = new ArrayList<StatistikEintrag>();
										// Sollte die ArrayList hier den selben Namen haben??
										// vielleicht eher "geloesteAufgaben"?
										// 
	/**
	 * vielleicht kann man auch soetwas wie overallStats schon direkt als Attribut abspeichern, sobald ein Neuer Eintrag hinzukommt
	 * Ist vielleicht sinnvoller bzw. ressourcenschonender als bei bestimmten Berechnungen jedesmal den gesamten Datensatz durch zugehen
	 * Beispiel:
	 * long geloesteAufgaben;
	 * long gesamteAufgaben;
	 * wieder optional: long bearbeitungszeit; (in Minuten so als Bonus um mittzuteilen wie viel man schon gelernt hat)
	 *  -(Jannik)
	 */
	
	
	
	// Konstruktor
	Statistiken(User User, Date Datum) { //Nutzer und Datum werden initial gesetzt 
		this.User = User;			   //eine sp�tere �nderung ist nicht vorgesehen
		this.Datum = Datum;			   //daher auch keine set-Methoden f�r User/Datum
	}

	
	//Methoden
	public void schreiben() {
	//Methode um die Statistik in die Nutzer-Datei zu schreiben
		
	}
	
	public User getUser() { 
		return User;
	}

	public Date getDatum() {
		return Datum;
	}

	public void setAufgabe(Exercise aufgabe) { //Methode k�nnte besser addAufgabe hei�en?
		/**
		 * Diese Methode k�nnte vielleicht auch sowas wie addEintrag() hei�en.
		 * -(Jannik)
		 */
		//Exercise.add(aufgabe);
	}

}
