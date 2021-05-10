package de.nrw.hspv.statistics;

import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;

public interface zuStatistik {

	public void addEintrag(User user, Exercise aufgabe);
}
