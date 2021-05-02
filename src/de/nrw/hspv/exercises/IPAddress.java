package de.nrw.hspv.exercises;
import java.util.Random;

public class IPAddress {
	//Author: Jannik
	protected int[] values = new int[4];
	
	

	
	//default Konstruktor
	public IPAddress() {
		
	}
	
	//konstruktor um direkt Werte zu übergeben
	public IPAddress(int[] values) {
		for(int i = 0; i<4; i++) {
			this.values[i] = values[i];
		}
	}
	
	
	public String toStringIpFormat() {
		StringBuilder tempSB = new StringBuilder();
		tempSB.append(values[0]);
		for(int i = 1; i<=3; i++) {
			tempSB.append(".");
			tempSB.append(values[i]);
		}
		
		return tempSB.toString();
	}
	
	//nicht sicher ob in der Form nötig
	
	
	
	//Getter und Setter Methoden
	public int getValue(int position) {
		return this.values[position];
	}
	
	public int[] getValues() {
		return this.values;
	}
	
}
