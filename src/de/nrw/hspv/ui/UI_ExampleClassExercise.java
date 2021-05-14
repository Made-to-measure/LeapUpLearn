package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.nrw.hspv.exercises.ExampleClassExercise;

public class UI_ExampleClassExercise extends JPanel { //UI-Klasse muss JPanel erweitern
	/*
	 * Diese Klasse dient als Beispiel zur Erweiterung des Programms um eine weiter Aufgabe
	 * Um diese Klasse anzuzeigen folge bitte unseren Anweiseungen in der MainFrame
	 */
	ExampleClassExercise currentExercise;	//die aktuelle Aufgabe
	
	UI_ExampleClassExercise(){
		
		setLayout(new BorderLayout(0,0)); //setzt das Grundlegende Layout
		
		//Erzeuge ButtonPanel zur steuerung der Aufgabe
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		//erzeuge Button zur Steuerung der Aufgabe
		JButton btnPruefe = new JButton("\u00DCberpr\u00FCfe");
		btnPruefe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//hier die überprüfe Methode von ExampleClassExercise aufrufen
			}
		});
		buttonPanel.add(btnPruefe);
		
		
		//erzeuge Panel für die Ausgestaltung der Aufgabe
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		//Ab hier kannst du deine Aufgabe ausgestalten füge die Komponenten bitte dem CenterPanel hinzu
		centerPanel.add(new JLabel("Dies ist ein BeispielLabel"));
		
		
		App.logger.log(Level.INFO, "BeispielUI erstsellt");
	}
	
}
