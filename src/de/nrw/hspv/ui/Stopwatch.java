package de.nrw.hspv.ui;

public class Stopwatch {
/**
 * Klasse zur Messung und Anzeige der ben \u00d6 tigten Zeit
 * 
 * @version 1.0
 * @author Christian
 */
	private long starttime;
	private long stoptime;
	double elapsedTime;
	String Zeit = new String();
	
	public void start() {
		starttime = System.currentTimeMillis();
	}
	
	public void stop() {
		stoptime = System.currentTimeMillis();
	}
	
	public double getElapsedMin() {
		elapsedTime = (stoptime - starttime)/1000/60;
		System.out.println(elapsedTime);
		Zeit = String.valueOf(elapsedTime);
		TimePanel.setLblZeit(Zeit);
		System.out.println(Zeit);
		return elapsedTime;
	}
}