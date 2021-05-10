package de.nrw.hspv.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.login.Login;
import de.nrw.hspv.statistics.Statistiken;

import javax.swing.JLabel;

	/**
	 * Erzeugt das Panel fuer die Darstellung der Statistik
	 * 
	 * @author Janis
	 * @version 1.0
	 */
public class UIStatistics extends JPanel {

	public UIStatistics() {
		
		//Anzahl geloester Aufgaben und gesamter Aufgaben je Aufgabentyp für aktiven User
		int anzahlIP = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.IPAddresse);
		int anzahlIPRichtig = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.IPAddresse, true);
		int anzahlZahlensys = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.Zahlensysteme);
		int anzahlZahlensysRichtig = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.Zahlensysteme, true);
		int anzahlIT = anzahlIP +anzahlZahlensys;
		int anzahlITRichtig = anzahlIPRichtig + anzahlZahlensysRichtig;
		int anzahlLogik = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.Logik);
		int anzahlLogikRichtig = Statistiken.getAnzahl(Login.aktiverUser.name, Aufgabentyp.Logik, true);
		int anzahlMathe = anzahlLogik;
		int anzahlMatheRichtig = anzahlLogikRichtig;
		

		
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		//JLabel fuer Kategorien
		JLabel lblIT = new JLabel("Grundlagen IT");
		springLayout.putConstraint(SpringLayout.NORTH, lblIT, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblIT, 10, SpringLayout.WEST, this);
		add(lblIT);
		
		JLabel lblIP = new JLabel("IP Adressen");
		springLayout.putConstraint(SpringLayout.NORTH, lblIP, 6, SpringLayout.SOUTH, lblIT);
		springLayout.putConstraint(SpringLayout.WEST, lblIP, 30, SpringLayout.WEST, this);
		add(lblIP);
		
		JLabel lblZahlensyteme = new JLabel("Zahlensysteme");
		springLayout.putConstraint(SpringLayout.NORTH, lblZahlensyteme, 6, SpringLayout.SOUTH, lblIP);
		springLayout.putConstraint(SpringLayout.WEST, lblZahlensyteme, 0, SpringLayout.WEST, lblIP);
		add(lblZahlensyteme);
		
		JLabel lblMathe = new JLabel("Mathe");
		springLayout.putConstraint(SpringLayout.NORTH, lblMathe, 6, SpringLayout.SOUTH, lblZahlensyteme);
		springLayout.putConstraint(SpringLayout.WEST, lblMathe, 10, SpringLayout.WEST, this);
		add(lblMathe);
		
		JLabel lblLogik = new JLabel("Logik");
		springLayout.putConstraint(SpringLayout.NORTH, lblLogik, 6, SpringLayout.SOUTH, lblMathe);
		springLayout.putConstraint(SpringLayout.WEST, lblLogik, 0, SpringLayout.WEST, lblIP);
		add(lblLogik);
		
		//JLabel fuer Anzahl geloester Aufgaben zu bearbeiteten Aufgaben
		JLabel lblITWerte = new JLabel(Integer.toString(anzahlITRichtig) + "/" + Integer.toString(anzahlIT));
		springLayout.putConstraint(SpringLayout.NORTH, lblITWerte, 0, SpringLayout.NORTH, lblIT);
		springLayout.putConstraint(SpringLayout.WEST, lblITWerte, 72, SpringLayout.EAST, lblIT);
		add(lblITWerte);
		
		JLabel lblIPWerte = new JLabel(Integer.toString(anzahlIPRichtig) + "/" + Integer.toString(anzahlIP));
		springLayout.putConstraint(SpringLayout.NORTH, lblIPWerte, 0, SpringLayout.NORTH, lblIP);
		springLayout.putConstraint(SpringLayout.WEST, lblIPWerte, 0, SpringLayout.WEST, lblITWerte);
		add(lblIPWerte);
		
		JLabel lblZahlensystemeWerte = new JLabel(Integer.toString(anzahlZahlensysRichtig) + "/" + Integer.toString(anzahlZahlensys));
		springLayout.putConstraint(SpringLayout.WEST, lblZahlensystemeWerte, 0, SpringLayout.WEST, lblITWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, lblZahlensystemeWerte, 0, SpringLayout.SOUTH, lblZahlensyteme);
		add(lblZahlensystemeWerte);
		
		JLabel lblMatheWerte = new JLabel(Integer.toString(anzahlMatheRichtig) + "/" + Integer.toString(anzahlMathe));
		springLayout.putConstraint(SpringLayout.NORTH, lblMatheWerte, 0, SpringLayout.NORTH, lblMathe);
		springLayout.putConstraint(SpringLayout.WEST, lblMatheWerte, 0, SpringLayout.WEST, lblITWerte);
		add(lblMatheWerte);
		
		JLabel lblLogikWerte = new JLabel(Integer.toString(anzahlLogikRichtig) + "/" + Integer.toString(anzahlLogik));
		springLayout.putConstraint(SpringLayout.NORTH, lblLogikWerte, 0, SpringLayout.NORTH, lblLogik);
		springLayout.putConstraint(SpringLayout.WEST, lblLogikWerte, 0, SpringLayout.WEST, lblITWerte);
		add(lblLogikWerte);

	}
}
