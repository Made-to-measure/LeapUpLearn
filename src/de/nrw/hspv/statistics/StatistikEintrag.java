package de.nrw.hspv.statistics;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;

public class StatistikEintrag implements Serializable{
	/**
	 * Klasse für einen Eintrag in die Statistik
	 *  
	 * @author Janis
	 * @version 1.0
	 */
	
	User aktiverUser;				//Attribute eines Statistikeintrags
	boolean geloest;
	long id;
	Aufgabentyp aufgabentyp;
	
	StatistikEintrag(User user, Exercise aufgabe) {		//Konstruktor
		this.aktiverUser = user;
		this.geloest = aufgabe.geloest;
		this.id = aufgabe.id;
		this.aufgabentyp = aufgabe.aufgabentyp;
	}
	
	/**
	 * Gibt den Statistikeintrag als String zurueck
	 */
	@Override
	public String toString() {
		return "Aufgabe " + id + " vom Typ " + aufgabentyp + ", geloest: " + geloest + " von " + aktiverUser.name;
	}
	
}
