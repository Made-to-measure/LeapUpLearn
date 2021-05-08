package de.nrw.hspv.exercises;

public class ZahlensystemExercise {
	public boolean geloest;
	public long id;
	public int zahl;												//Zahl, die der Aufgabe zugrunde liegt
	public int typ;
	public String dezZahl;											//Zahlen als String für Umformung
	public String binZahl;
	public String oktZahl;
	public String hexZahl;
	
	public ZahlensystemExercise() {										//Standardkonstruktor wird überschrieben
		geloest = true;												//geloest wird standardmäßig auf true gesetzt, bei Fehler in Überprüfung auf falsch
		id = System.currentTimeMillis();							//ID der Aufgabe ist die aktuelle Zeit in ms
		zahl = (int) (Math.random()*4095+1);						//zufällige Zahl zwischen 1 und 4095
		typ = (int) (Math.random()*4);							//zufällige Zahl zwischen 0 und 3 für 4 Fälle
		
		if(typ == 0) {												//Fall 1: Dezimalzahl gegeben
			dezZahl = Integer.toString(zahl);
		}
		else if(typ == 1) {											//Fall 2: Binärzahl gegeben
			binZahl = Integer.toBinaryString(zahl);
		}
		else if(typ == 2) {											//Fall 3: Oktalzahl gegeben
			oktZahl = Integer.toOctalString(zahl);
		}
		else if(typ ==3) {											//Fall 4: Hexadezimahlzahl gegeben
			hexZahl = Integer.toHexString(zahl);
		}
	}
	
	public boolean check(String dezZahl, String binZahl, String oktZahl, String hexZahl) {		//Methode zur Überprüfung von Eingaben
		if(zahl != Integer.parseInt(dezZahl)) {	//Hier könnten Exceptions auftreten				//Prüfung dezimale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(binZahl, 2)) {										//Prüfung binäre Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(oktZahl, 8)) {										//Prüfung oktale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(hexZahl, 16)) {										//Prüfung hexadezimale Eingabe
			geloest = false;
		}
		return geloest;																			//boolean gelöst wird zurückgegeben
	}
}
