package de.nrw.hspv.statistics;

import java.util.Calendar;
import java.util.Date;

import de.nrw.hspv.exercises.Aufgabentyp;

public class StatistikEintrag {
	/*
	 * Jannik:
	 * Diese Klasse
	 */
	
	
	Date datum;
	Aufgabentyp aufgabentyp;
	boolean geloest;
	//long loesungszeit; //als optionales Feature
	
	public StatistikEintrag(Aufgabentyp aufgabentyp, boolean geloest) {
		this.aufgabentyp = aufgabentyp;
		this.geloest = geloest;
		this.datum = Calendar.getInstance().getTime(); //Jannik: Keine Ahnung ob das funktioniert einfach mal eine Idee
	}
}
