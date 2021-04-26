package de.nrw.hspv;

import java.util.ArrayList;
import java.util.Date;

public class Statistiken {
	public User User;
	public Date Datum;
	public ArrayList<Aufgabe> Aufgaben = new ArrayList<Aufgabe>();
										// Sollte die ArrayList hier den selben Namen haben??
										// vielleicht eher "geloesteAufgaben"?
										// 

	// Konstruktor
	Statistiken(User User, Date Datum) { //Nutzer und Datum werden initial gesetzt 
		this.User = User;			   //eine spätere Änderung ist nicht vorgesehen
		this.Datum = Datum;			   //daher auch keine set-Methoden für User/Datum
	}

	
	//Methoden
	public void schreiben() {
	//Methode um die Statistik in die Nutzer-Datei zu schreiben
		
	}
	
	public User getUser() { 
		return User;
	}

	public Date getDatum() {
		return Datum;
	}

	public void setAufgabe(Aufgabe aufgabe) { //Methode könnte besser addAufgabe heißen?
		/**
		 * Diese Methode könnte vielleicht auch sowas wie addEintrag() heißen.
		 * -(Jannik)
		 */
		Aufgaben.add(aufgabe);
	}

}
