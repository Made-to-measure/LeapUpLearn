package de.nrw.hspv.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
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
	private JTextField tfEingabeDezi;
	private JTextField tfEingabeBinaer;
	private JTextField tfEingabeOktal;
	private JTextField tfEingabeHexa;
	ZahlensystemExercise aufgabe;

	/**
	 * Create the panel.
	 */
	public UIZahlensysteme() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnPruefe = new JButton("\u00DCberpr\u00FCfe");
		btnPruefe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dez = UIZahlensysteme.this.tfEingabeDezi.getText();	//Abfrage der Textfelder
				String bin = UIZahlensysteme.this.tfEingabeBinaer.getText();
				String okt = UIZahlensysteme.this.tfEingabeOktal.getText();
				String hex = UIZahlensysteme.this.tfEingabeHexa.getText();
				if(aufgabe.check(dez, bin, okt, hex)) {							//Überprüfung der Eingaben
					System.out.println("Aufgabe gelöst");
				}
				else {
					System.out.println("Aufgabe nicht gelöst");
				}
				aktualisiereAufgabe();											//neue Aufgabe wird erstellt und dargestellt
			}
			
			
		});
		panel.add(btnPruefe);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel lblBinaer = new JLabel("Bin\u00E4r");
		lblBinaer.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblBinaer);
		
		JLabel lblDezimal_1_1 = new JLabel("Dezimal");
		sl_panel_1.putConstraint(SpringLayout.EAST, lblDezimal_1_1, 0, SpringLayout.EAST, lblBinaer);
		lblDezimal_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblDezimal_1_1);
		
		JLabel lblOktal = new JLabel("Oktal");
		sl_panel_1.putConstraint(SpringLayout.EAST, lblBinaer, 0, SpringLayout.EAST, lblOktal);
		lblOktal.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblOktal);
		
		JLabel lblHexa = new JLabel("Hexadezimal");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblHexa, 12, SpringLayout.SOUTH, lblOktal);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblOktal, 0, SpringLayout.EAST, lblHexa);
		lblHexa.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHexa);
		
		tfEingabeDezi = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeDezi, 22, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblDezimal_1_1, 3, SpringLayout.NORTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeDezi, -212, SpringLayout.EAST, panel_1);
		panel_1.add(tfEingabeDezi);
		tfEingabeDezi.setColumns(10);
		
		tfEingabeBinaer = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeBinaer, 6, SpringLayout.SOUTH, tfEingabeDezi);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeBinaer, 6, SpringLayout.EAST, lblBinaer);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeBinaer, -212, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblBinaer, 3, SpringLayout.NORTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeDezi, 0, SpringLayout.WEST, tfEingabeBinaer);
		tfEingabeBinaer.setColumns(10);
		panel_1.add(tfEingabeBinaer);
		
		tfEingabeOktal = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeOktal, 6, SpringLayout.SOUTH, tfEingabeBinaer);
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeOktal, 6, SpringLayout.EAST, lblOktal);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeOktal, -212, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblOktal, 3, SpringLayout.NORTH, tfEingabeOktal);
		tfEingabeOktal.setColumns(10);
		panel_1.add(tfEingabeOktal);
		
		tfEingabeHexa = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.WEST, tfEingabeHexa, 88, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblHexa, -6, SpringLayout.WEST, tfEingabeHexa);
		sl_panel_1.putConstraint(SpringLayout.NORTH, tfEingabeHexa, -3, SpringLayout.NORTH, lblHexa);
		sl_panel_1.putConstraint(SpringLayout.EAST, tfEingabeHexa, -212, SpringLayout.EAST, panel_1);
		tfEingabeHexa.setColumns(10);
		panel_1.add(tfEingabeHexa);
		aktualisiereAufgabe();

	}
	public void aktualisiereAufgabe() {						//Methode um neue Aufgabe vom Typ AufgabeZahlensysteme zu erzeugen und darzustellen
		tfEingabeDezi.setEnabled(true);					//Alle Textfelder edititierbar setzen
		tfEingabeBinaer.setEnabled(true);
		tfEingabeOktal.setEnabled(true);
		tfEingabeHexa.setEnabled(true);
		
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
