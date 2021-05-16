package de.nrw.hspv.exercises;

import java.util.logging.Level;

import de.nrw.hspv.login.Login;
import de.nrw.hspv.ui.App;

/**
 * Beispielklasse zur erweiterung des Programms
 * @author Jannik, Janis, Christian
 *
 */
public class ExampleClassExercise extends Exercise{
	/*
	 * Diese Klasse ist eine Beispielklasse um eine neue Aufgabe hinzuzufügen
	 * Wichtig ist, dass die Aufgabe über die folgenden Parameter (id, geloest, Aufgabentyp)
	 * verfügt
	 * 
	 * Um ein Beispiel anzuzeigen kommentiere die Operation ThemenPanel.add(btnBeispielKurs); in der Mainframe aus (ca. Zeile 150)
	 * 
	 * Die Anleitung für die GUI-Komponente ist in der UI_ExampleClassExercise und in der MainFrame zu finden
	 * Die Anleitung für die Anzeige der Statistik ist in der UIStatistics zu finden
	 */
	
	
	
	ExampleClassExercise(){
		geloest = true;												//geloest wird standardmaessig auf true gesetzt, bei Fehler in Ueberpruefung auf falsch
		id = System.currentTimeMillis();						//ID der Aufgabe ist die aktuelle Zeit in ms
		/*
		 * Der Aufgabentyp muss für jede neuen Aufgabe in 
		 * der Enumeration Aufgabentyp.java gesetzt werden
		 */
		aufgabentyp = Aufgabentyp.Beispielaufgabe;	//Setze Aufgabentyp
		App.logger.log(Level.INFO, "Beispielaufgabe erzeugt");	//logge die Erstellung der Aufgabe
	}
	
	public boolean ueberpruefe() {
		//Hier soll ausgewertet werden, ob Die Aufgabe richtig geloest wurde 
		addEintrag(Login.aktiverUser, this); //Diese Methode erstellt einen neuen Eintrag in der Statistik
		return geloest; //Es wird der Wahrheitswert zurückgegeben
	}
}
