package de.nrw.hspv;

import java.util.Date;

public abstract class Exercise {

	public boolean geloest;
	public Date Datum;
	public int zeit; 
	
	public abstract boolean �berpr�fe;
	
	public abstract StatistikEintrag updateStats();
		
}
