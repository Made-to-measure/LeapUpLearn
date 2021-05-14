package de.nrw.hspv.statistics;

import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;

/**
 * Interface fuer Klassen, die einen Eintrag zur Statistik hinzufuegen sollen
 * @author Janis
 *@version 1.0
 */
public interface zuStatistik {

	public void addEintrag(User user, Exercise aufgabe);	//Methode zum hinzufuegen eines Eintrags mit User und Aufgabe muss implementiert werden
}
