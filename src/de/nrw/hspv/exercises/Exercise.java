package de.nrw.hspv.exercises;

import de.nrw.hspv.login.User;
import de.nrw.hspv.statistics.StatistikEintrag;
import de.nrw.hspv.statistics.Statistiken;
import de.nrw.hspv.statistics.zuStatistik;

//import java.util.Date;

public abstract class Exercise implements zuStatistik{
	/**
	 *Abstrakte Klasse Aufgaben
	 * 
	 * @author Janis
	 * @version 1.0
	 */

	public boolean geloest;
	public long id;
	public Aufgabentyp aufgabentyp;
	
	@Override
	public void addEintrag(User user, Exercise aufgabe) {
		Statistiken.addEintrag(user, aufgabe);
	}
	
//	public abstract boolean ueberpruefe();
	
//	public abstract StatistikEintrag updateStats();
		
}

 
	 
 
