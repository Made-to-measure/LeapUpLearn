package de.nrw.hspv;

import java.util.Date;

public abstract class Aufgabe {

	public boolean geloest;
	public Date Datum;
	public int zeit; 
	
	public boolean pruefe() {
		return geloest;
	}
	
	public void updateStats() {
		
	}
}
