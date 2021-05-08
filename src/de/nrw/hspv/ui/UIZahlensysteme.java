package de.nrw.hspv.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.nrw.hspv.exercises.ZahlensystemExercise;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

public class UIZahlensysteme extends JPanel {
	
	/**
	 * Darstellung der Zahlensystemaufgabe
	 * 
	 * @author Janis
	 * @version 1.0
	 */
	public JTextField tfEingabeDezi;
	public JTextField tfEingabeBinaer;
	public JTextField tfEingabeOktal;
	public JTextField tfEingabeHexa;
	public JLabel lblInfo;
	public boolean inBearbeitung = true;
	ZahlensystemExercise aufgabe;
	
	public UIZahlensysteme() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		//Button zur Überprüfung der Aufgabe
		JButton btnPruefe = new JButton("\u00DCberpr\u00FCfe");
		btnPruefe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(inBearbeitung) {
					String dez = UIZahlensysteme.this.tfEingabeDezi.getText();	//Abfrage der Textfelder
					String bin = UIZahlensysteme.this.tfEingabeBinaer.getText();
					String okt = UIZahlensysteme.this.tfEingabeOktal.getText();
					String hex = UIZahlensysteme.this.tfEingabeHexa.getText();
					if(aufgabe.check(dez, bin, okt, hex)) {							//Überprüfung der Eingaben
						lblInfo.setText("Richtig!");
					}
					else {
						lblInfo.setText("Leider nicht ganz richtig, hier siehst du die Lösung:");
						if(aufgabe.zahl != Integer.parseInt(dez)) {
							tfEingabeDezi.setBackground(Color.red);
							tfEingabeDezi.setText(aufgabe.dezZahl);
						}
						if(aufgabe.zahl != Integer.parseUnsignedInt(bin, 2)) {
							tfEingabeBinaer.setBackground(Color.red);
							tfEingabeBinaer.setText(aufgabe.binZahl);
						}
						if(aufgabe.zahl != Integer.parseUnsignedInt(okt, 8)) {
							tfEingabeOktal.setBackground(Color.red);
							tfEingabeOktal.setText(aufgabe.oktZahl);
						}
						if(aufgabe.zahl != Integer.parseUnsignedInt(hex, 16)) {
							tfEingabeHexa.setBackground(Color.red);
							tfEingabeHexa.setText(aufgabe.hexZahl);
						}
					}
					btnPruefe.setText("Neue Aufgabe");
					inBearbeitung = false;
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
		
		//Textfelder für die Eingabe
		tfEingabeDezi = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblDezimal_1_1, 3, SpringLayout.NORTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeDezi, 48, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeDezi, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeDezi, -212, SpringLayout.EAST, panel_1);
		panel_1.add(tfEingabeDezi);
		tfEingabeDezi.setColumns(10);
		
		tfEingabeBinaer = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblBinaer, 3, SpringLayout.NORTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblBinaer, -6, SpringLayout.WEST, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeBinaer, 6, SpringLayout.SOUTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeBinaer, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeBinaer, -212, SpringLayout.EAST, panel_1);
		tfEingabeBinaer.setColumns(10);
		panel_1.add(tfEingabeBinaer);
		
		tfEingabeOktal = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblOktal, 3, SpringLayout.NORTH, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblOktal, -6, SpringLayout.WEST, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeOktal, 6, SpringLayout.SOUTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeOktal, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeOktal, -212, SpringLayout.EAST, panel_1);
		tfEingabeOktal.setColumns(10);
		panel_1.add(tfEingabeOktal);
		
		tfEingabeHexa = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblHexa, 3, SpringLayout.NORTH, tfEingabeHexa);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblHexa, -6, SpringLayout.WEST, tfEingabeHexa);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeHexa, 9, SpringLayout.SOUTH, tfEingabeOktal);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeHexa, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeHexa, -212, SpringLayout.EAST, panel_1);
		tfEingabeHexa.setColumns(10);
		panel_1.add(tfEingabeHexa);
		
		//Label für Infos
		lblInfo = new JLabel("Die Aufgabe kann jetzt bearbeitet werden!");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblInfo, 0, SpringLayout.WEST, lblDezimal_1_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblInfo, -13, SpringLayout.NORTH, tfEingabeDezi);
		panel_1.add(lblInfo);
		aktualisiereAufgabe();

	}
	public void aktualisiereAufgabe() {						//Methode um neue Aufgabe vom Typ AufgabeZahlensysteme zu erzeugen und darzustellen
		lblInfo.setText("Die Aufgabe kann jetzt bearbeitet werden!");		//InfoLabel aktualisieren
		
		tfEingabeDezi.setEnabled(true);					//Alle Textfelder edititierbar setzen
		tfEingabeBinaer.setEnabled(true);
		tfEingabeOktal.setEnabled(true);
		tfEingabeHexa.setEnabled(true);
		
		tfEingabeDezi.setBackground(Color.white);		//Alle Textfelder wieder auf weiß setzen
		tfEingabeBinaer.setBackground(Color.white);
		tfEingabeOktal.setBackground(Color.white);
		tfEingabeHexa.setBackground(Color.white);
		
		aufgabe = new ZahlensystemExercise();				//neue Aufgabe wird zugewiesen
		tfEingabeDezi.setText(aufgabe.dezZahl);			//Textfelder werden entsprechend zugewiesen
		tfEingabeBinaer.setText(aufgabe.binZahl);
		tfEingabeOktal.setText(aufgabe.oktZahl);
		tfEingabeHexa.setText(aufgabe.hexZahl);
		
		switch (aufgabe.typ) {								//Je nach Typ der Ausgangszahl wird jeweiliges Textfeld auf nicht editierbar gesetzt
		case 0:
			tfEingabeDezi.setEnabled(false);
			break;
		case 1:
			tfEingabeBinaer.setEnabled(false);
			break;
		case 2:
			tfEingabeOktal.setEnabled(false);
			break;
		case 3:
			tfEingabeHexa.setEnabled(false);
			break;
		}
	}
}
