package de.nrw.hspv.exercises;
import java.util.Random;

public class IPAddress {
	/**
	 *Klasse zur Erzeugung und Bearbeitung von IP Adressen
	 * 
	 * @author Jannik
	 * @version 1.0
	 */
	//Author: Jannik
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
	 * 
	 * @return gibt die IP-Adresse als String zurück
	 */
	public String toStringIpFormat() {
		StringBuilder tempSB = new StringBuilder();
		tempSB.append(values[0]);
		for(int i = 1; i<=3; i++) {
			tempSB.append(".");
			tempSB.append(values[i]);
		}
		
		return tempSB.toString();
	}

	
	/**
	 * @param position des werts der IP
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
