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
	
	public JTextField tfEingabeDezi;
	public JTextField tfEingabeBinaer;
	public JTextField tfEingabeOktal;
	public JTextField tfEingabeHexa;
	public JLabel lblInfo;
	public boolean inBearbeitung = true;
	public boolean verifiedDezi;
	public boolean verifiedBinaer;
	public boolean verifiedOktal;
	public boolean verifiedHexa;
	ZahlensystemExercise aufgabe;
	
	/**
	 * Erzeugt das Panel fuer die Darstellung der Zahlensystemaufgabe
	 */
	public UIZahlensysteme() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		//Button zur �berpr�fung der Aufgabe
		JButton btnPruefe = new JButton("\u00DCberpr\u00FCfe");
		btnPruefe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(inBearbeitung) {
					if(verifiedDezi && verifiedBinaer && verifiedOktal && verifiedHexa) { //Format der Eingaben wird geprueft
						String dez = UIZahlensysteme.this.tfEingabeDezi.getText();	//Abfrage der Textfelder
						String bin = UIZahlensysteme.this.tfEingabeBinaer.getText();
						String okt = UIZahlensysteme.this.tfEingabeOktal.getText();
						String hex = UIZahlensysteme.this.tfEingabeHexa.getText();
						if(aufgabe.ueberpruefe(dez, bin, okt, hex)) {							//�berpr�fung der Eingaben
							lblInfo.setText("Richtig!");
						}
						else {
							lblInfo.setText("Leider nicht ganz richtig, hier siehst du die L�sung:");
							if(aufgabe.zahl != Integer.parseInt(dez)) {
								tfEingabeDezi.setBackground(Color.red);
								tfEingabeDezi.setText(aufgabe.dezZahl);
								tfEingabeDezi.setEnabled(false);
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
						btnPruefe.setText("Neue Aufgabe");
						inBearbeitung = false;
					}
					else {
						new HinweisFenster("Bitte \u00DCberpr\u00FCfe deine Eingaben!");
					}
				}
				else {
					aktualisiereAufgabe();						//neue Aufgabe wird erstellt und dargestellt
					btnPruefe.setText("\u00DCberpr\u00FCfe");
					inBearbeitung = true;
				}
															
			}
			
			
		});
		panel.add(btnPruefe);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		//Label, die die Textfelder bezeichnen
		JLabel lblBinaer = new JLabel("Bin\u00E4r");
		lblBinaer.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblBinaer);
		
		JLabel lblDezimal_1_1 = new JLabel("Dezimal");
		sl_panel_1.putConstraint(SpringLayout.EAST, lblDezimal_1_1, 0, SpringLayout.EAST, lblBinaer);
		lblDezimal_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblDezimal_1_1);
		
		JLabel lblOktal = new JLabel("Oktal");
		lblOktal.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblOktal);
		
		JLabel lblHexa = new JLabel("Hexadezimal");
		lblHexa.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHexa);
		
		//Textfelder f�r die Eingabe
		tfEingabeDezi = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblDezimal_1_1, 3, SpringLayout.NORTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeDezi, 48, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeDezi, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeDezi, -212, SpringLayout.EAST, panel_1);
		tfEingabeDezi.setColumns(10);
		tfEingabeDezi.setInputVerifier(new InputVerifier() {		//InputVerifier fuer Eingabe der Zahl

			@Override
			public boolean verify(JComponent input) {
				try {
					Integer.parseInt(((JTextField) input).getText());		
				}
				catch (Exception e) {
					verifiedDezi = false;							//Wenn Eingabe zu Exception fuehren wuerde setze verified auf false
					return false;
				}
				verifiedDezi = true;								//Wenn Eingabe korrekt ist wird verified auf true
				return true;
			}
			
		});
		panel_1.add(tfEingabeDezi);
		
		
		tfEingabeBinaer = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblBinaer, 3, SpringLayout.NORTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblBinaer, -6, SpringLayout.WEST, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeBinaer, 6, SpringLayout.SOUTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeBinaer, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeBinaer, -212, SpringLayout.EAST, panel_1);
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
		panel_1.add(tfEingabeBinaer);
		
		tfEingabeOktal = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblOktal, 3, SpringLayout.NORTH, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblOktal, -6, SpringLayout.WEST, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeOktal, 6, SpringLayout.SOUTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeOktal, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeOktal, -212, SpringLayout.EAST, panel_1);
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
		panel_1.add(tfEingabeOktal);
		
		tfEingabeHexa = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblHexa, 3, SpringLayout.NORTH, tfEingabeHexa);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblHexa, -6, SpringLayout.WEST, tfEingabeHexa);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeHexa, 9, SpringLayout.SOUTH, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeHexa, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeHexa, -212, SpringLayout.EAST, panel_1);
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
		panel_1.add(tfEingabeHexa);
		
		//Label f�r Infos
		lblInfo = new JLabel("Die Aufgabe kann jetzt bearbeitet werden!");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblInfo, 0, SpringLayout.WEST, lblDezimal_1_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblInfo, -13, SpringLayout.NORTH, tfEingabeDezi);
		panel_1.add(lblInfo);
		
		//Erzeuge Aufgabe Zahlensysteme
		aktualisiereAufgabe();

		App.logger.log(Level.INFO, "UIZahlensysteme erstellt");
	}
	
	/**
	 * laedt eine neue Aufgabe und aktualisiert die Darstellung
	 */
	public void aktualisiereAufgabe() {						//Methode um neue Aufgabe vom Typ AufgabeZahlensysteme zu erzeugen und darzustellen
		lblInfo.setText("Die Aufgabe kann jetzt bearbeitet werden!");		//InfoLabel aktualisieren
		
		tfEingabeDezi.setEnabled(true);					//Alle Textfelder edititierbar setzen
		tfEingabeBinaer.setEnabled(true);
		tfEingabeOktal.setEnabled(true);
		tfEingabeHexa.setEnabled(true);
		
		tfEingabeDezi.setBackground(Color.white);		//Alle Textfelder wieder auf wei� setzen
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
		switch (aufgabe.typ) {								//Je nach Typ der Ausgangszahl wird jeweiliges Textfeld auf nicht editierbar gesetzt
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
