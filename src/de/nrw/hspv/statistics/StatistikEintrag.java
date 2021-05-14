package de.nrw.hspv.statistics;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;

/**
 * Klasse für einen Eintrag in die Statistik
 * @author Janis
 * @version 1.0
 */
public class StatistikEintrag implements Serializable{			//Serializable fuer Eintrag in Statistik
		
	//Attribute eines Statistikeintrags
	User aktiverUser;				
	boolean geloest;
	long id;
	Aufgabentyp aufgabentyp;
	
	/**
	 * Erzeugt einen neuen Statistikeintrag
	 * @param user der Aufgabe bearbeitet hat
	 * @param aufgabe die bearbeitet wurde
	 */
	StatistikEintrag(User user, Exercise aufgabe) {		//Konstruktor fuer Statistikeintrag
		this.aktiverUser = user;
		this.geloest = aufgabe.geloest;
		this.id = aufgabe.id;
		this.aufgabentyp = aufgabe.aufgabentyp;
	}
	
	/**
	 * Wandelt den Eintrag in String um
	 * @return der Statistikeintrag als String
	 */
	@Override
	public String toString() {
		return "Aufgabe " + id + " vom Typ " + aufgabentyp + ", geloest: " + geloest + " von " + aktiverUser.name;
	}
	
}
