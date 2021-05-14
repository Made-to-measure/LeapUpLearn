package de.nrw.hspv.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import de.nrw.hspv.exercises.ZahlensystemExercise;
import de.nrw.hspv.ui.HinweisFenster;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

/**
 * Panel fuer die Darstellung der Zahlensystemaufgabe
 * @author Janis
 * @version 1.0
 */
public class UIZahlensysteme extends JPanel {
	
	public JTextField tfEingabeDezi;		//Die GUI benoetigt Eingabefelder fuer Dezimal, Binaer, Oktal und Hexadezimal
	public JTextField tfEingabeBinaer;
	public JTextField tfEingabeOktal;
	public JTextField tfEingabeHexa;
	public JLabel lblInfo;					//JLabel fuer Informationen
	public boolean inBearbeitung = true;	//boolean fuer Zustand ob in Bearbeitung oder ob Loesung angezeigt wird
	public boolean verifiedDezi;			//Verifier fuer einzelne Textfelder
	public boolean verifiedBinaer;
	public boolean verifiedOktal;
	public boolean verifiedHexa;
	ZahlensystemExercise aufgabe;			//Zahlensystemaufgabe, die dargestellt werden soll
	
	/**
	 * Erzeugt das Panel fuer die Darstellung der Zahlensystemaufgabe
	 */
	public UIZahlensysteme() {
		setLayout(new BorderLayout(0, 0));		//BorderLayout fuer Aufteilung
		
		JPanel panelUnten = new JPanel();		//panelUnten fuer Button
		add(panelUnten, BorderLayout.SOUTH);	//panelUnten soll untem im BorderLayout liegen
		panelUnten.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));	//Button soll rechts liegen
		
		//Button zur Überprüfung der Aufgabe
		JButton btnPruefe = new JButton("\u00DCberpr\u00FCfe");
		btnPruefe.addActionListener(new ActionListener() {		//ActionListener fuer Button

			@Override
			public void actionPerformed(ActionEvent e) {
				if(inBearbeitung) {		//wenn Aufgabe bearbeitet wird soll mit Button ueberpruefung ausgeloest werden
					if(verifiedDezi && verifiedBinaer && verifiedOktal && verifiedHexa) { //Format der Eingaben wird geprueft
						String dez = UIZahlensysteme.this.tfEingabeDezi.getText();	//Abfrage der Textfelder
						String bin = UIZahlensysteme.this.tfEingabeBinaer.getText();
						String okt = UIZahlensysteme.this.tfEingabeOktal.getText();
						String hex = UIZahlensysteme.this.tfEingabeHexa.getText();
						if(aufgabe.ueberpruefe(dez, bin, okt, hex)) {							//Überprüfung der Eingaben auf Richtigkeit
							lblInfo.setText("Richtig!");										//wenn richtig wird das im InfoLabel angezeigt
						}
						else {																	//wenn falsch
							lblInfo.setText("Leider nicht ganz richtig, hier siehst du die Lösung:");	//Infolabel soll diesen Text anzeigen
							if(aufgabe.zahl != Integer.parseInt(dez)) {									//Uerpruefung der Einzelnen Eingaben
								tfEingabeDezi.setBackground(Color.red);									//wenn falsche Eingabe soll TextFeld rot sein
								tfEingabeDezi.setText(aufgabe.dezZahl);									//richtige Loesung soll angezeigt werden
								tfEingabeDezi.setEnabled(false);										//Feld soll nicht bearbeitbar sein
							}
							if(aufgabe.zahl != Integer.parseUnsignedInt(bin, 2)) {
								tfEingabeBinaer.setBackground(Color.red);
								tfEingabeBinaer.setText(aufgabe.binZahl);
								tfEingabeBinaer.setEnabled(false);
							}
							if(aufgabe.zahl != Integer.parseUnsignedInt(okt, 8)) {
								tfEingabeOktal.setBackground(Color.red);
								tfEingabeOktal.setText(aufgabe.oktZahl);
								tfEingabeOktal.setEnabled(false);
							}
							if(aufgabe.zahl != Integer.parseUnsignedInt(hex, 16)) {
								tfEingabeHexa.setBackground(Color.red);
								tfEingabeHexa.setText(aufgabe.hexZahl);
								tfEingabeHexa.setEnabled(false);
							}
						}
						btnPruefe.setText("Neue Aufgabe");	//Button soll nach Ueberpruefung den Text "neue Aufgabe" anzeigen
						inBearbeitung = false;	//Zustand von 'in Bearbeitung' auf 'Loesung wird angezeigt' setzen
					}
					else {
						//Wenn die Eingaben nicht dem richtigen Format entsprechen soll HinweisFenster angezeigt werden
						new HinweisFenster("Bitte \u00DCberpr\u00FCfe deine Eingaben!");	
					}
				}
				else {		//Zustand ist nicht 'in Bearbeitung'
					aktualisiereAufgabe();						//neue Aufgabe wird erstellt und dargestellt
					btnPruefe.setText("\u00DCberpr\u00FCfe");	//Button-Text wird auf Ueberpruefen gesetzt
					inBearbeitung = true;						//Zustand wird auf 'in Bearbeitung' gesetzt
				}
															
			}
			
			
		});
		panelUnten.add(btnPruefe);			//Button wird panelUnten hinzugefuegt
		
		JPanel panelOben = new JPanel();	//panelOben fuer Darstellung der Aufgabe
		add(panelOben, BorderLayout.CENTER);	//panelOben soll im Center des BorderLayouts liegen
		//Aufgabenpanel soll SpringLayout fuer Darstellung der einzelnen Komponenten haben
		SpringLayout layoutAufgabe = new SpringLayout();	
		panelOben.setLayout(layoutAufgabe);
		
		//Label, die die Textfelder bezeichnen
		JLabel lblBinaer = new JLabel("Bin\u00E4r");	//Erzeugung des Labels fuer Benennung
		lblBinaer.setHorizontalAlignment(SwingConstants.TRAILING);	//Text soll rechtsbuendig sein
		panelOben.add(lblBinaer);		//Label wird panelOben hinzugefuegt
		
		JLabel lblDezimal = new JLabel("Dezimal");
		layoutAufgabe.putConstraint(SpringLayout.EAST, lblDezimal, 0, SpringLayout.EAST, lblBinaer);
		lblDezimal.setHorizontalAlignment(SwingConstants.TRAILING);
		panelOben.add(lblDezimal);
		
		JLabel lblOktal = new JLabel("Oktal");
		lblOktal.setHorizontalAlignment(SwingConstants.TRAILING);
		panelOben.add(lblOktal);
		
		JLabel lblHexa = new JLabel("Hexadezimal");
		lblHexa.setHorizontalAlignment(SwingConstants.TRAILING);
		panelOben.add(lblHexa);
		
		//Textfelder für die Eingabe
		tfEingabeDezi = new JTextField();
		layoutAufgabe.putConstraint(SpringLayout.NORTH, lblDezimal, 3, SpringLayout.NORTH, tfEingabeDezi);
		layoutAufgabe.putConstraint(SpringLayout.NORTH, tfEingabeDezi, 48, SpringLayout.NORTH, panelOben);
		layoutAufgabe.putConstraint(SpringLayout.WEST, tfEingabeDezi, 88, SpringLayout.WEST, panelOben);
		layoutAufgabe.putConstraint(SpringLayout.EAST, tfEingabeDezi, -212, SpringLayout.EAST, panelOben);
		tfEingabeDezi.setColumns(10);
		tfEingabeDezi.setInputVerifier(new InputVerifier() {		//InputVerifier fuer Eingabe der Zahl

			@Override
			public boolean verify(JComponent input) {
				try {
					Integer.parseInt(((JTextField) input).getText());	//parseInt muss auf Eingabe anwendbar sein	
				}
				catch (Exception e) {								
					verifiedDezi = false;	//Wenn Eingabe zu Exception fuehren wuerde setze verified auf false
					return false;
				}
				verifiedDezi = true;				//Wenn Eingabe korrekt ist wird verified auf true
				return true;
			}
			
		});
		panelOben.add(tfEingabeDezi);		//fuege Textfeld fuer Dezimaleingabe dem panelOben hinzu
		
		
		tfEingabeBinaer = new JTextField();
		layoutAufgabe.putConstraint(SpringLayout.NORTH, lblBinaer, 3, SpringLayout.NORTH, tfEingabeBinaer);
		layoutAufgabe.putConstraint(SpringLayout.EAST, lblBinaer, -6, SpringLayout.WEST, tfEingabeBinaer);
		layoutAufgabe.putConstraint(SpringLayout.NORTH, tfEingabeBinaer, 6, SpringLayout.SOUTH, tfEingabeDezi);
		layoutAufgabe.putConstraint(SpringLayout.WEST, tfEingabeBinaer, 88, SpringLayout.WEST, panelOben);
		layoutAufgabe.putConstraint(SpringLayout.EAST, tfEingabeBinaer, -212, SpringLayout.EAST, panelOben);
		tfEingabeBinaer.setColumns(10);
		tfEingabeBinaer.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				try {
					Integer.parseUnsignedInt(((JTextField) input).getText(), 2);
				}
				catch (Exception e) {
					verifiedBinaer = false;
					return false;
				}
				verifiedBinaer = true;
				return true;
			}
			
		});
		panelOben.add(tfEingabeBinaer);
		
		tfEingabeOktal = new JTextField();
		layoutAufgabe.putConstraint(SpringLayout.NORTH, lblOktal, 3, SpringLayout.NORTH, tfEingabeOktal);
		layoutAufgabe.putConstraint(SpringLayout.EAST, lblOktal, -6, SpringLayout.WEST, tfEingabeOktal);
		layoutAufgabe.putConstraint(SpringLayout.NORTH, tfEingabeOktal, 6, SpringLayout.SOUTH, tfEingabeBinaer);
		layoutAufgabe.putConstraint(SpringLayout.WEST, tfEingabeOktal, 88, SpringLayout.WEST, panelOben);
		layoutAufgabe.putConstraint(SpringLayout.EAST, tfEingabeOktal, -212, SpringLayout.EAST, panelOben);
		tfEingabeOktal.setColumns(10);
		tfEingabeOktal.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				try {
					Integer.parseUnsignedInt(((JTextField) input).getText(), 8);
				}
				catch (Exception e) {
					verifiedOktal = false;
					return false;
				}
				verifiedOktal = true;
				return true;
			}
			
		});
		panelOben.add(tfEingabeOktal);
		
		tfEingabeHexa = new JTextField();
		layoutAufgabe.putConstraint(SpringLayout.NORTH, lblHexa, 3, SpringLayout.NORTH, tfEingabeHexa);
		layoutAufgabe.putConstraint(SpringLayout.EAST, lblHexa, -6, SpringLayout.WEST, tfEingabeHexa);
		layoutAufgabe.putConstraint(SpringLayout.NORTH, tfEingabeHexa, 9, SpringLayout.SOUTH, tfEingabeOktal);
		layoutAufgabe.putConstraint(SpringLayout.WEST, tfEingabeHexa, 88, SpringLayout.WEST, panelOben);
		layoutAufgabe.putConstraint(SpringLayout.EAST, tfEingabeHexa, -212, SpringLayout.EAST, panelOben);
		tfEingabeHexa.setColumns(10);
		tfEingabeHexa.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				try {
					Integer.parseUnsignedInt(((JTextField) input).getText(), 16);
				}
				catch (Exception e) {
					verifiedHexa = false;
					return false;
				}
				verifiedHexa = true;
				return true;
			}
			
		});
		panelOben.add(tfEingabeHexa);
		
		//Label für Infos
		lblInfo = new JLabel("Die Aufgabe kann jetzt bearbeitet werden!"); //Inhalt des InfoLabels
		layoutAufgabe.putConstraint(SpringLayout.WEST, lblInfo, 0, SpringLayout.WEST, lblDezimal);
		layoutAufgabe.putConstraint(SpringLayout.SOUTH, lblInfo, -13, SpringLayout.NORTH, tfEingabeDezi);
		panelOben.add(lblInfo);	//Info wird zu panelOben hinzugefuegt
		
		//Erzeuge Aufgabe Zahlensysteme
		aktualisiereAufgabe();

		App.logger.log(Level.INFO, "UIZahlensysteme erstellt");
	}
	
	/**
	 * laedt eine neue Aufgabe und aktualisiert die Darstellung
	 */
	public void aktualisiereAufgabe() {	//Methode um neue Zahlensystemaufgabe zu erzeugen und darzustellen
		lblInfo.setText("Die Aufgabe kann jetzt bearbeitet werden!");		//InfoLabel aktualisieren
		
		tfEingabeDezi.setEnabled(true);					//Alle Textfelder edititierbar setzen
		tfEingabeBinaer.setEnabled(true);
		tfEingabeOktal.setEnabled(true);
		tfEingabeHexa.setEnabled(true);
		
		tfEingabeDezi.setBackground(Color.white);		//Alle Textfelder wieder auf weiß setzen
		tfEingabeBinaer.setBackground(Color.white);
		tfEingabeOktal.setBackground(Color.white);
		tfEingabeHexa.setBackground(Color.white);
		
		tfEingabeDezi.setText("");						//Inhalt aller Textfelder zuruecksetzen
		tfEingabeBinaer.setText("");
		tfEingabeOktal.setText("");
		tfEingabeHexa.setText("");
		
		verifiedDezi = false;							//Ueberpruefung der Eingaben auf false setzen
		verifiedBinaer = false;
		verifiedOktal = false;
		verifiedHexa = false;
		
		aufgabe = new ZahlensystemExercise();				//neue Aufgabe wird zugewiesen
															
		//Textfelder werden entsprechend zugewiesen
		switch (aufgabe.typ) {	//Je nach Typ der Ausgangszahl wird jeweiliges Textfeld auf nicht editierbar gesetzt
		case 0:												
			tfEingabeDezi.setText(aufgabe.dezZahl);
			tfEingabeDezi.setEnabled(false);
			verifiedDezi = true;							
			break;
		case 1:
			tfEingabeBinaer.setText(aufgabe.binZahl);
			tfEingabeBinaer.setEnabled(false);
			verifiedBinaer = true;
			break;
		case 2:
			tfEingabeOktal.setText(aufgabe.oktZahl);
			tfEingabeOktal.setEnabled(false);
			verifiedOktal = true;
			break;
		case 3:
			tfEingabeHexa.setText(aufgabe.hexZahl);
			tfEingabeHexa.setEnabled(false);
			verifiedHexa = true;
			break;
		}
		
	}
}
