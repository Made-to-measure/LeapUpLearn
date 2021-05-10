package de.nrw.hspv.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import de.nrw.hspv.exercises.Aufgabentyp;
import de.nrw.hspv.login.Login;
import de.nrw.hspv.statistics.Statistiken;

import javax.swing.JLabel;
import java.awt.Color;

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
		
		//Panellaengen fuer Visualisierung der Anzahl richtig geloester Aufgaben
		int laengeIT = anzahlITRichtig * (100/anzahlIT);		//100 soll gesamte Laenge des Balkens sein
		int laengeIP = anzahlIPRichtig * (100/anzahlIP);
		int laengeZahlensys = anzahlZahlensysRichtig * (100/anzahlZahlensys);
		int laengeMathe = 50;	//Standardwerte um Division durch 0 zu vermeiden solange Mathe nicht implementiert ist
		int laengeLogik = 50;
		
		//Panel fuer Visualisierung der Anzahl richtig geloester Aufgaben
		JPanel panelIT = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelIT, 0, SpringLayout.NORTH, lblITWerte );
		springLayout.putConstraint(SpringLayout.WEST, panelIT, 60, SpringLayout.WEST, lblITWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, panelIT, 0, SpringLayout.SOUTH, lblITWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelIT, laengeIT, SpringLayout.WEST, panelIT);
		panelIT.setBackground(Color.GREEN);
		add(panelIT);
		
		JPanel panelIP = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelIP, 0, SpringLayout.NORTH, lblIPWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelIP, 60, SpringLayout.WEST, lblIPWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, panelIP, 0, SpringLayout.SOUTH, lblIPWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelIP, laengeIP, SpringLayout.WEST, panelIP);
		panelIP.setBackground(Color.GREEN);
		add(panelIP);
		
		JPanel panelZahlensys = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelZahlensys, 0, SpringLayout.NORTH, lblZahlensystemeWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelZahlensys, 60, SpringLayout.WEST, lblZahlensystemeWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, panelZahlensys, 0, SpringLayout.SOUTH, lblZahlensystemeWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelZahlensys, laengeZahlensys, SpringLayout.WEST, panelZahlensys);
		panelZahlensys.setBackground(Color.GREEN);
		add(panelZahlensys);
		
		JPanel panelMathe = new JPanel();
		panelMathe.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, panelMathe, 0, SpringLayout.NORTH, lblMatheWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelMathe, 60, SpringLayout.WEST, lblMatheWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, panelMathe, 0, SpringLayout.SOUTH, lblMatheWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelMathe, laengeMathe, SpringLayout.WEST, panelMathe);
		add(panelMathe);
		
		JPanel panelLogik = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelLogik, 0, SpringLayout.NORTH, lblLogikWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelLogik, 60, SpringLayout.WEST, lblLogikWerte);
		springLayout.putConstraint(SpringLayout.SOUTH, panelLogik, 0, SpringLayout.SOUTH, lblLogikWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelLogik, laengeLogik, SpringLayout.WEST, panelLogik);
		panelLogik.setBackground(Color.GREEN);
		add(panelLogik);
		
		//Panel zur Visualisierung der Anzahl falsch geloester Aufgaben
		JPanel panelITFalsch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelITFalsch, 0, SpringLayout.NORTH, lblITWerte );
		springLayout.putConstraint(SpringLayout.WEST, panelITFalsch, 0, SpringLayout.EAST, panelIT);
		springLayout.putConstraint(SpringLayout.SOUTH, panelITFalsch, 0, SpringLayout.SOUTH, lblITWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelITFalsch, 100, SpringLayout.WEST, panelIT);
		panelITFalsch.setBackground(Color.RED);
		add(panelITFalsch);
		
		JPanel panelIPFalsch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelIPFalsch, 0, SpringLayout.NORTH, lblIPWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelIPFalsch, 0, SpringLayout.EAST, panelIP);
		springLayout.putConstraint(SpringLayout.SOUTH, panelIPFalsch, 0, SpringLayout.SOUTH, lblIPWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelIPFalsch, 100, SpringLayout.WEST, panelIP);
		panelIPFalsch.setBackground(Color.RED);
		add(panelIPFalsch);
		
		JPanel panelZahlensysFalsch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelZahlensysFalsch, 0, SpringLayout.NORTH, lblZahlensystemeWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelZahlensysFalsch, 0, SpringLayout.EAST, panelZahlensys);
		springLayout.putConstraint(SpringLayout.SOUTH, panelZahlensysFalsch, 0, SpringLayout.SOUTH, lblZahlensystemeWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelZahlensysFalsch, 100, SpringLayout.WEST, panelZahlensys);
		panelZahlensysFalsch.setBackground(Color.RED);
		add(panelZahlensysFalsch);
		
		JPanel panelMatheFalsch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelMatheFalsch, 0, SpringLayout.NORTH, lblMatheWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelMatheFalsch, 0, SpringLayout.EAST, panelMathe);
		springLayout.putConstraint(SpringLayout.SOUTH, panelMatheFalsch, 0, SpringLayout.SOUTH, lblMatheWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelMatheFalsch, 100, SpringLayout.WEST, panelMathe);
		panelMatheFalsch.setBackground(Color.RED);
		add(panelMatheFalsch);
		
		JPanel panelLogikFalsch = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelLogikFalsch, 0, SpringLayout.NORTH, lblLogikWerte);
		springLayout.putConstraint(SpringLayout.WEST, panelLogikFalsch, 0, SpringLayout.EAST, panelLogik);
		springLayout.putConstraint(SpringLayout.SOUTH, panelLogikFalsch, 0, SpringLayout.SOUTH, lblLogikWerte);
		springLayout.putConstraint(SpringLayout.EAST, panelLogikFalsch, 100, SpringLayout.WEST, panelLogik);
		panelLogikFalsch.setBackground(Color.RED);
		add(panelLogikFalsch);

	}
}
