package de.nrw.hspv;

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

	public void setName(String name) { // Wohl �berfl�ssig, sehe keine Notwendigkeit den Namen �ndern zu m�ssen
		this.Name = name; 				// zumal dann die Idee mit den Dateinamen nicht mehr ohne
	}									 // aufwendiges Fehlerhandling funktioniert
}
