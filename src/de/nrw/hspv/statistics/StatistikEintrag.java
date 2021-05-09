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
	 * @param Aufgabentyp aufgabentyp
	 * @param geloest boolean
	 * 
	 * @author Janis, Jannik
	 * @version 1.0
	 */
	
	User aktiverUser;
	boolean geloest;
	long id;
	Aufgabentyp aufgabentyp;
	
	StatistikEintrag(User user, Exercise aufgabe) {
		this.aktiverUser = user;
		this.geloest = aufgabe.geloest;
		this.id = aufgabe.id;
		this.aufgabentyp = aufgabe.aufgabentyp;
	}
	
	@Override
	public String toString() {
		return "Aufgabe " + id + " vom Typ " + aufgabentyp + ", geloest: " + geloest + " von " + aktiverUser.name;
	}
	
//	Date datum;
//	Aufgabentyp aufgabentyp;
//	boolean geloest;
//	//long loesungszeit; //als optionales Feature
//	
//	public StatistikEintrag(Aufgabentyp aufgabentyp, boolean geloest) {
//		this.aufgabentyp = aufgabentyp;
//		this.geloest = geloest;
//		this.datum = Calendar.getInstance().getTime(); //Jannik: Keine Ahnung ob das funktioniert einfach mal eine Idee
//	}
}
