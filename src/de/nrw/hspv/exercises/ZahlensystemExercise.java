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
	public String dezZahl;											//Zahlen als String für Umformung
	public String binZahl;
	public String oktZahl;
	public String hexZahl;
	
	/**
	 * Konstruiert neue Zahlensystemaufgabe
	 */
	public ZahlensystemExercise() {										//Standardkonstruktor wird überschrieben
		geloest = true;												//geloest wird standardmäßig auf true gesetzt, bei Fehler in Überprüfung auf falsch
		id = System.currentTimeMillis();							//ID der Aufgabe ist die aktuelle Zeit in ms
		aufgabentyp = Aufgabentyp.Zahlensysteme;
		zahl = (int) (Math.random()*4095+1);						//zufällige Zahl zwischen 1 und 4095
		typ = (int) (Math.random()*4);							//zufällige Zahl zwischen 0 und 3 für 4 Fälle fuer vorgegebene Zahl
															
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
	public boolean ueberpruefe(String dezZahl, String binZahl, String oktZahl, String hexZahl) {		//Methode zur Überprüfung von Eingaben
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
		addEintrag(Login.aktiverUser, this);
		return geloest;																			//boolean gelöst wird zurückgegeben
	}
}
