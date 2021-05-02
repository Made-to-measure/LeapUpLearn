package de.nrw.hspv.ui;

import de.nrw.hspv.statistics.Statistiken;

public class User {
	String Name;
	Statistiken stats;
	
	
	//Konstruktor 
	User(String name) {
		this.Name = name; //
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) { // Wohl überflüssig, sehe keine Notwendigkeit den Namen ändern zu müssen
		this.Name = name; 				// zumal dann die Idee mit den Dateinamen nicht mehr ohne
	}									 // aufwendiges Fehlerhandling funktioniert
}
