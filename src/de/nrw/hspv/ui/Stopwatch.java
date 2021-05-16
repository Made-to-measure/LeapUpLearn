package de.nrw.hspv.ui;

/**
 * Klasse zur Messung und Anzeige der benoetigten Zeit
 * 
 * @version 1.0
 * @author Christian
 */
public class Stopwatch {

	private long starttime;
	private long stoptime;
	double elapsedTime;
	String Zeit = new String();
	
	/**
	 * aktuelle Zeit (in Millisekunden) wird als Startzeit gespeichert
	 */
	public void start() {
		starttime = System.currentTimeMillis();
	}
	
	/**
	 * aktuelle Zeit (in Millisekunden) wird als Endzeit gesetzt
	 */
	public void stop() {
	
		stoptime = System.currentTimeMillis();
	}
	

	/**
	 * Differenz aus Start und Endzeit
	 * 
	 * @return elapsedTime
	 */
	public double getElapsedMin() {
		elapsedTime = (stoptime - starttime)/1000/60;
		System.out.println(elapsedTime);
		Zeit = String.valueOf(elapsedTime);
		TimePanel.setLblZeit(Zeit);
		System.out.println(Zeit);
		return elapsedTime;
	}
}