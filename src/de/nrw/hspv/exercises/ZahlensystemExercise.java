package de.nrw.hspv.exercises;

import java.awt.Color;
import java.util.logging.Level;

import de.nrw.hspv.login.Login;
import de.nrw.hspv.ui.App;
import de.nrw.hspv.ui.UIZahlensysteme;

/**
 * Inhalt und Methoden der Zahlensystemaufgabe
 * 
 * @author Janis
 * @version 1.0
 */
public class ZahlensystemExercise extends Exercise {				//erbt von Exercise
	
	public int zahl;												//Zahl, die der Aufgabe zugrunde liegt
	public int typ;
	public String dezZahl;											//Zahlen als String für Umformung in jeweiliges Zahlensystem
	public String binZahl;
	public String oktZahl;
	public String hexZahl;
	
	/**
	 * Konstruiert neue Zahlensystemaufgabe
	 */
	public ZahlensystemExercise() {										//Standardkonstruktor wird überschrieben
		geloest = true;												//geloest wird standardmaeßig auf true gesetzt, bei Fehler in Ueberpruefung auf falsch
		id = System.currentTimeMillis();							//ID der Aufgabe ist die aktuelle Zeit in ms
		aufgabentyp = Aufgabentyp.Zahlensysteme;
		zahl = (int) (Math.random()*4095+1);						//zufaellige Zahl zwischen 1 und 4095
		typ = (int) (Math.random()*4);							//zufaellige Zahl zwischen 0 und 3 für 4 Faelle fuer vorgegebene Zahl
															
		dezZahl = Integer.toString(zahl);						//Umformung der Zahl in String fuer jeweiliges Zahlensystem
		binZahl = Integer.toBinaryString(zahl);
		oktZahl = Integer.toOctalString(zahl);
		hexZahl = Integer.toHexString(zahl);
		App.logger.log(Level.INFO, "Neue Zahlensystemaufgabe generiert");
		
	}
	
	/**
	 * Gleicht uebergebene Werte mit der Aufgabe ab
	 * @param dezZahl die abzugleichende Dezimalzahl
	 * @param binZahl die abzugleichende Binaerzahl
	 * @param oktZahl die abzugleichende Oktalzahl
	 * @param hexZahl die abzugleichende Hexadezimalzahl
	 * @return die boolean geloest
	 */
	public boolean ueberpruefe(String dezZahl, String binZahl, String oktZahl, String hexZahl) {		//Methode zur Ueberpruefung von Eingaben
		if(zahl != Integer.parseInt(dezZahl)) {													//Pruefung dezimale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(binZahl, 2)) {										//Pruefung binaere Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(oktZahl, 8)) {										//Pruefung oktale Eingabe
			geloest = false;
		}
		if(zahl != Integer.parseUnsignedInt(hexZahl, 16)) {										//Pruefung hexadezimale Eingabe
			geloest = false;
		}
		addEintrag(Login.aktiverUser, this);
		return geloest;																			//boolean geloest wird zurueckgegeben
	}
}
