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

	public boolean geloest;				//Zeigt an, ob eine Aufgabe geloest wurde
	public long id;						//Die ID ist die System.currentTimeMillis() zum Zeitpunkt der Aufgabenerzeugung
	public Aufgabentyp aufgabentyp;		//Der Aufgabentyp
	
	@Override
	public void addEintrag(User user, Exercise aufgabe) {
		Statistiken.addEintrag(user, aufgabe);
	}
		
}

 
	 
 
