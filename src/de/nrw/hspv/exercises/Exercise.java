package de.nrw.hspv.exercises;

import de.nrw.hspv.statistics.StatistikEintrag;

//import java.util.Date;

public abstract class Exercise {
	/**
	 *Abstrakte Klasse Aufgaben
	 * 
	 * @author 
	 * @version 1.0
	 */

//	public boolean geloest;
//	public Date Datum;
//	public int zeit; 
	public abstract boolean überprüfe();
	
	public abstract StatistikEintrag updateStats();
		
}

 
	 
 
