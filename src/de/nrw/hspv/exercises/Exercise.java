package de.nrw.hspv.exercises;

import de.nrw.hspv.login.User;
import de.nrw.hspv.statistics.StatistikEintrag;
import de.nrw.hspv.statistics.Statistiken;
import de.nrw.hspv.statistics.zuStatistik;

/**
 * Allgemeine Klasse Aufgabe, die Grundlage fuer verschiedene Aufgabentypen ist
 * @author Janis
 * @version 1.0
 */

public abstract class Exercise implements zuStatistik{

	public boolean geloest;				//Zeigt an, ob eine Aufgabe geloest wurde
	public long id;						//Die ID ist die System.currentTimeMillis() zum Zeitpunkt der Aufgabenerzeugung
	public Aufgabentyp aufgabentyp;		//Der Aufgabentyp
	
	/**
	 * Fuegt einen Eintrag zur Statistik hinzu
	 * @param user, der die Aufgabe bearbeitet hat
	 * @param aufgabe, die hinzugefuegt werden soll
	 */
	@Override
	public void addEintrag(User user, Exercise aufgabe) {
		Statistiken.addEintrag(user, aufgabe);
	}
		
}

 
	 
 
