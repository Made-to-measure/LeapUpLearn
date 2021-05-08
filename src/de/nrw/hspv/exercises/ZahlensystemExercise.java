package de.nrw.hspv.exercises;

public class ZahlensystemExercise {
	public boolean geloest;
	public long id;
	public int zahl;												//Zahl, die der Aufgabe zugrunde liegt
	public int typ;
	public String dezZahl;											//Zahlen als String f�r Umformung
	public String binZahl;
	public String oktZahl;
	public String hexZahl;
	
	public ZahlensystemExercise() {										//Standardkonstruktor wird �berschrieben
		geloest = true;												//geloest wird standardm��ig auf true gesetzt, bei Fehler in �berpr�fung auf falsch
		id = System.currentTimeMillis();							//ID der Aufgabe ist die aktuelle Zeit in ms
		zahl = (int) (Math.random()*4095+1);						//zuf�llige Zahl zwischen 1 und 4095
		typ = (int) (Math.random()*4);							//zuf�llige Zahl zwischen 0 und 3 f�r 4 F�lle
		
		if(typ == 0) {												//Fall 1: Dezimalzahl gegeben
			dezZahl = Integer.toString(zahl);
		}
		else if(typ == 1) {											//Fall 2: Bin�rzahl gegeben
			binZahl = Integer.toBinaryString(zahl);
		}
		else if(typ == 2) {											//Fall 3: Oktalzahl gegeben
			oktZahl = Integer.toOctalString(zahl);
		}
		else if(typ ==3) {											//Fall 4: Hexadezimahlzahl gegeben
			hexZahl = Integer.toHexString(zahl);
		}
	}
	
	public boolean check(String dezZahl, String binZahl, String oktZahl, String hexZahl) {		//Methode zur �berpr�fung von Eingaben
		if(zahl != Integer.parseInt(dezZahl)) {	//Hier k�nnten Exceptions auftreten				//Pr�fung dezimale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(binZahl, 2)) {										//Pr�fung bin�re Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(oktZahl, 8)) {										//Pr�fung oktale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(hexZahl, 16)) {										//Pr�fung hexadezimale Eingabe
			geloest = false;
		}
		return geloest;																			//boolean gel�st wird zur�ckgegeben
	}
}
