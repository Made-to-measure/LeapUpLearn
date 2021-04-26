package de.nrw.hspv;

import java.util.Date;

public abstract class Exercise {

	public boolean geloest;
	public Date Datum;
	public int zeit; 
	
	public abstract boolean überprüfe;
	
	public abstract StatistikEintrag updateStats();
		
}
