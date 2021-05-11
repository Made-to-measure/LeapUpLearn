package de.nrw.hspv.exercises;

import java.util.logging.Level;

import de.nrw.hspv.ui.App;

public class IPAddress {
	/**
	 *Klasse zur Erzeugung und Bearbeitung von IP Adressen
	 * 
	 * @author Jannik
	 * @version 1.0
	 */
	protected int[] values = new int[4];
	
	

	
	/**
	 * default Konstruktor
	 */
	public IPAddress() {
		
	}
	
	/**
	 * Konstrutor der direkt Werte zuweist
	 * @param values sind die einzelnen Werte der IP-Adresse
	 */
	public IPAddress(int[] values) {
		for(int i = 0; i<4; i++) {
			this.values[i] = values[i];
		}
	}
	
	/**
	 * Dient dazu eine IPAdress zu einem String zu formatieren
	 * Wird genutz zur Ausgabe
	 * @return gibt die IP-Adresse als String zurück
	 */
	public String toStringIpFormat() {
		StringBuilder tempSB = new StringBuilder(); //erstelle temporaeren StringBuilder
		tempSB.append(values[0]);	//fuege die erste Position an
		for(int i = 1; i<=3; i++) {
			tempSB.append(".");	//trenne durch Punkt
			tempSB.append(values[i]);	//fuege naechste Position an
		}
		App.logger.log(Level.INFO, "IP in String formatiert");
		return tempSB.toString();
	}

	
	/**
	 * @param Position des Werts der IP
	 * @return gibt den Integer der jeweiligen Position zurück
	 */
	public int getValue(int position) {
		return this.values[position];
	}
	/**
	 * getValues gibt das komplette Array zurück
	 * @return Values als int-Array
	 */
	public int[] getValues() {
		return this.values;
	}
	
}
