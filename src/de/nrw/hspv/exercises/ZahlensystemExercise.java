package de.nrw.hspv.exercises;

import java.awt.Color;

import de.nrw.hspv.login.Login;
import de.nrw.hspv.ui.UIZahlensysteme;

public class ZahlensystemExercise extends Exercise {				//erbt von Exercise
	
	/**
	 * Inhalt und Methoden der Zahlensystemaufgabe
	 * 
	 * @author Janis
	 * @version 1.0
	 */
	
	public int zahl;												//Zahl, die der Aufgabe zugrunde liegt
	public int typ;
	public String dezZahl;											//Zahlen als String f�r Umformung
	public String binZahl;
	public String oktZahl;
	public String hexZahl;
	
	/**
	 * Konstruiert neue Zahlensystemaufgabe
	 */
	public ZahlensystemExercise() {										//Standardkonstruktor wird �berschrieben
		geloest = true;												//geloest wird standardm��ig auf true gesetzt, bei Fehler in �berpr�fung auf falsch
		id = System.currentTimeMillis();							//ID der Aufgabe ist die aktuelle Zeit in ms
		aufgabentyp = Aufgabentyp.Zahlensysteme;
		zahl = (int) (Math.random()*4095+1);						//zuf�llige Zahl zwischen 1 und 4095
		typ = (int) (Math.random()*4);							//zuf�llige Zahl zwischen 0 und 3 f�r 4 F�lle fuer vorgegebene Zahl
															
		dezZahl = Integer.toString(zahl);
		binZahl = Integer.toBinaryString(zahl);
		oktZahl = Integer.toOctalString(zahl);
		hexZahl = Integer.toHexString(zahl);
		
	}
	
	/**
	 * Gleicht uebergebene Werte mit der Aufgabe ab
	 * @param dezZahl die abzugleichende Dezimalzahl
	 * @param binZahl die abzugleichende Binaerzahl
	 * @param oktZahl die abzugleichende Oktalzahl
	 * @param hexZahl die abzugleichende Hexadezimalzahl
	 * @return die boolean geloest
	 */
	public boolean ueberpruefe(String dezZahl, String binZahl, String oktZahl, String hexZahl) {		//Methode zur �berpr�fung von Eingaben
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
		addEintrag(Login.aktiverUser, this);
		return geloest;																			//boolean gel�st wird zur�ckgegeben
	}
}
