package de.nrw.hspv.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.nrw.hspv.exercises.IPExercise;

public class UI_IPRuntime extends JPanel {
	JPanel[] addressPanels = new JPanel[8];
	IPExercise currentExercise;
	JTextField[] fieldArr;
	JPanel startPanel;
	JPanel runtimePanel;
	private int page = 1;
	Color lightRed = new Color(255,102,102);
	Color lightGreen = new Color(102,255,102);
	boolean[] results;
	JButton btnRightCorner;
	
	UI_IPRuntime(){
	
	currentExercise = new IPExercise();
	fieldArr = new JTextField[8];
	
	setLayout(new BorderLayout(5,5));

	setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	
	JPanel panel_2 = new JPanel();
	FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
	flowLayout_1.setAlignment(FlowLayout.RIGHT);
	add(panel_2, BorderLayout.SOUTH);
	
	JPanel panel_3 = new JPanel();
	FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
	flowLayout.setAlignment(FlowLayout.LEFT);
	add(panel_3, BorderLayout.NORTH);
	
	JLabel labelFormatAdvice = new JLabel("Fülle die leeren Textfelder! Benutze dabei bitte folgendes Format: xxx.xxx.xxx.xxx");
	labelFormatAdvice.setHorizontalAlignment(SwingConstants.LEFT);
	panel_3.add(labelFormatAdvice);
	
//	JButton btnNewButton_1 = new JButton("Abbruch");
//	btnNewButton_1.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//		}
//	});
//	panel_2.add(btnNewButton_1);
	
	btnRightCorner = new JButton("Überprüfen");
	btnRightCorner.setEnabled(false);
	btnRightCorner.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(page == 1) {
				showInputs();
				btnRightCorner.setText("Zeige Lösung");
				labelFormatAdvice.setText("Nun siehst du, ob deine Lösung richtig war!");
				page++;
				
			}
			else if(page ==2) {
				showResults();
				btnRightCorner.setText("Nächste Aufgabe");
				labelFormatAdvice.setText("Hier siehts du die richtigen Ergebnisse!");
				page++;
			}
			else if(page == 3) {
				loadNewExercise();
				btnRightCorner.setText("Überprüfen");
				labelFormatAdvice.setText("Fülle die leeren Textfelder! Benutze dabei bitte folgendes Format: xxx.xxx.xxx.xxx");
				page=1;
			}
		}
	});
	
	
	panel_2.add(btnRightCorner);
	
	add(new PanelIpZeilen(), BorderLayout.CENTER);
	loadFirstExercise();
	}


		class PanelIpZeilen extends JPanel{
			int panelLenght = 400;
			
			
			PanelIpZeilen(){
				super();
				SpringLayout sl_layer2center = new SpringLayout();
				this.setLayout(sl_layer2center);
				
				//Reihe Netzwerkadresse
				JLabel label1 = new JLabel("Netzwerkadresse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label1, 10, SpringLayout.NORTH, this);
				sl_layer2center.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, this);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label1, 30, SpringLayout.NORTH, this);
				sl_layer2center.putConstraint(SpringLayout.EAST, label1, 159, SpringLayout.WEST, this);
				this.add(label1);
				
				addressPanels[0] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[0], 0, SpringLayout.NORTH, label1);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[0], 6, SpringLayout.EAST, label1);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[0], 0, SpringLayout.SOUTH, label1);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[0], panelLenght, SpringLayout.EAST, label1);
				this.add(addressPanels[0]);
				addressPanels[0].setLayout(new GridLayout(0, 1, 0, 0));
				
				//Reihe Hostadresse
				JLabel label2 = new JLabel("ausgewählte Hostadresse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label2, 6, SpringLayout.SOUTH, label1);
				sl_layer2center.putConstraint(SpringLayout.WEST, label2, 0, SpringLayout.WEST, label1);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label2, 46, SpringLayout.NORTH, label1);
				sl_layer2center.putConstraint(SpringLayout.EAST, label2, 149, SpringLayout.WEST, label1);
				this.add(label2);
				
				addressPanels[1] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[1], 0, SpringLayout.NORTH, label2);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[1], 6, SpringLayout.EAST, label2);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[1], 0, SpringLayout.SOUTH, label2);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[1], panelLenght, SpringLayout.EAST, label2);
				this.add(addressPanels[1]);
				addressPanels[1].setLayout(new GridLayout(1, 0, 0, 0));
				
				//Subnetzmaske
				JLabel label3 = new JLabel("Subneztmaske");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label3, 6, SpringLayout.SOUTH, label2);
				sl_layer2center.putConstraint(SpringLayout.WEST, label3, 0, SpringLayout.WEST, label2);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label3, 46, SpringLayout.NORTH, label2);
				sl_layer2center.putConstraint(SpringLayout.EAST, label3, 149, SpringLayout.WEST, label2);
				this.add(label3);
				
				addressPanels[2] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[2], 0, SpringLayout.NORTH, label3);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[2], 6, SpringLayout.EAST, label3);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[2], 0, SpringLayout.SOUTH, label3);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[2], panelLenght, SpringLayout.EAST, label3);
				this.add(addressPanels[2]);
				addressPanels[2].setLayout(new GridLayout(1, 0, 0, 0));
				
				//Erste Adresse
				JLabel label4 = new JLabel("erste Adresse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label4, 6, SpringLayout.SOUTH, label3);
				sl_layer2center.putConstraint(SpringLayout.WEST, label4, 0, SpringLayout.WEST, label3);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label4, 46, SpringLayout.NORTH, label3);
				sl_layer2center.putConstraint(SpringLayout.EAST, label4, 149, SpringLayout.WEST, label3);
				this.add(label4);
				
				addressPanels[3] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[3], 0, SpringLayout.NORTH, label4);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[3], 6, SpringLayout.EAST, label4);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[3], 0, SpringLayout.SOUTH, label4);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[3], panelLenght, SpringLayout.EAST, label4);
				this.add(addressPanels[3]);
				addressPanels[3].setLayout(new GridLayout(1, 0, 0, 0));
				
				//letze Adresse
				JLabel label5 = new JLabel("letzte Adresse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label5, 6, SpringLayout.SOUTH, label4);
				sl_layer2center.putConstraint(SpringLayout.WEST, label5, 0, SpringLayout.WEST, label4);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label5, 46, SpringLayout.NORTH, label4);
				sl_layer2center.putConstraint(SpringLayout.EAST, label5, 149, SpringLayout.WEST, label4);
				this.add(label5);
				
				addressPanels[4] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[4], 0, SpringLayout.NORTH, label5);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[4], 6, SpringLayout.EAST, label5);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[4], 0, SpringLayout.SOUTH, label5);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[4], panelLenght, SpringLayout.EAST, label5);
				this.add(addressPanels[4]);
				addressPanels[4].setLayout(new GridLayout(1, 0, 0, 0));
				
				//Broadcastadresse
				JLabel label6 = new JLabel("Broadcastadresse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label6, 6, SpringLayout.SOUTH, label5);
				sl_layer2center.putConstraint(SpringLayout.WEST, label6, 0, SpringLayout.WEST, label5);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label6, 46, SpringLayout.NORTH, label5);
				sl_layer2center.putConstraint(SpringLayout.EAST, label6, 149, SpringLayout.WEST, label5);
				this.add(label6);
				
				addressPanels[5] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[5], 0, SpringLayout.NORTH, label6);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[5], 6, SpringLayout.EAST, label6);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[5], 0, SpringLayout.SOUTH, label6);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[5], panelLenght, SpringLayout.EAST, label6);
				this.add(addressPanels[5]);
				addressPanels[5].setLayout(new GridLayout(1, 0, 0, 0));
				
				//CIDR-Klasse
				JLabel label7 = new JLabel("CIDR-Klasse");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label7, 6, SpringLayout.SOUTH, label6);
				sl_layer2center.putConstraint(SpringLayout.WEST, label7, 0, SpringLayout.WEST, label6);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label7, 46, SpringLayout.NORTH, label6);
				sl_layer2center.putConstraint(SpringLayout.EAST, label7, 149, SpringLayout.WEST, label6);
				this.add(label7);
				
				addressPanels[6] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[6], 0, SpringLayout.NORTH, label7);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[6], 6, SpringLayout.EAST, label7);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[6], 0, SpringLayout.SOUTH, label7);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[6], panelLenght, SpringLayout.EAST, label7);
				this.add(addressPanels[6]);
				addressPanels[6].setLayout(new GridLayout(1, 0, 0, 0));
				
				//CIDR-Klasse
				JLabel label8 = new JLabel("CIDR-Binär");
				sl_layer2center.putConstraint(SpringLayout.NORTH, label8, 6, SpringLayout.SOUTH, label7);
				sl_layer2center.putConstraint(SpringLayout.WEST, label8, 0, SpringLayout.WEST, label7);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, label8, 46, SpringLayout.NORTH, label7);
				sl_layer2center.putConstraint(SpringLayout.EAST, label8, 149, SpringLayout.WEST, label7);
				this.add(label8);
						
				addressPanels[7] = new JPanel();
				sl_layer2center.putConstraint(SpringLayout.NORTH, addressPanels[7], 0, SpringLayout.NORTH, label8);
				sl_layer2center.putConstraint(SpringLayout.WEST, addressPanels[7], 6, SpringLayout.EAST, label8);
				sl_layer2center.putConstraint(SpringLayout.SOUTH, addressPanels[7], 0, SpringLayout.SOUTH, label8);
				sl_layer2center.putConstraint(SpringLayout.EAST, addressPanels[7], panelLenght, SpringLayout.EAST, label8);
				this.add(addressPanels[7]);
				addressPanels[7].setLayout(new GridLayout(1, 0, 0, 0));
			}
		}


		public void loadFirstExercise() {
			boolean[] boolArr = this.currentExercise.getExerciseType();
			for(int i=0; i<8; i++) {
				if(boolArr[i]) {
					this.addressPanels[i].add(fieldArr[i] =new JTextField());
					fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
					fieldArr[i].setEditable(false);
					fieldArr[i].setFocusable(false);
					fieldArr[i].setBackground(Color.LIGHT_GRAY);
					fieldArr[i].setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				else {
					this.addressPanels[i].add(fieldArr[i] = new JTextField());	
				}
				fieldArr[i].getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
						  	statusRightButton();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  statusRightButton();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  statusRightButton();
						  }

				});
			}
		}
		
		private void statusRightButton() {
			btnRightCorner.setEnabled(checkFields());
		}
		
		public void loadNewExercise() {
			this.currentExercise = new IPExercise();
			boolean[] boolArr = this.currentExercise.getExerciseType();
			for(int i=0; i<8; i++) {
				fieldArr[i].setBackground(Color.WHITE);
				fieldArr[i].setEditable(true);
				fieldArr[i].setFocusable(true);
				if(boolArr[i]) {
					fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
					fieldArr[i].setEditable(false);
					fieldArr[i].setFocusable(false);
					fieldArr[i].setBackground(Color.LIGHT_GRAY);
					fieldArr[i].setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				else {
					fieldArr[i].setText("");
				}
				}
			
		}
		
		public String[] getInputs() {
			String[] tempArr = new String[fieldArr.length];
			for(int i=0; i<tempArr.length; i++) {
				tempArr[i] = fieldArr[i].getText();
				tempArr[i] = tempArr[i].trim();
			}
			return tempArr;
		}
		
		private int[] copyArray(int[] values) {
			int[] copyArr = new int[values.length];	//erzeuge Temporäres array um nicht "auf dem Objekt" zu arbeiten
			for (int i = 0; i<values.length; i++) {
				copyArr[i] = values[i];	//kopiere
			}
			return copyArr;
		}
		
		public void showInputs() {
			this.results = this.currentExercise.validateInputs(getInputs());
			for(int i = 0; i < results.length; i++) {
				if(!this.currentExercise.getExerciseType()[i]) {
					fieldArr[i].setEditable(false);
					if(results[i]) {
						fieldArr[i].setBackground(lightGreen);
					}
					else {
						fieldArr[i].setBackground(lightRed);
					}
				}
			}
		}
		
		public void showResults() {
			for(int i = 0; i< fieldArr.length; i++) {
				if(!this.currentExercise.getExerciseType()[i]) {
					if(i == 1 && fieldArr[i].getBackground() == lightRed) {
						fieldArr[i].setText(fieldArr[i].getText() + " liegt außerhalb des Adressbereichs");
						fieldArr[i].setBackground(new Color(255,102,102)); //light red
					}
					else{
						fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
					}
				}	 
				
			}
		}
		
		public boolean checkFields() {
			for(int i = 0; i<fieldArr.length; i++) {
				if(fieldArr[i].getText().length() <= 0) {
					return false;
				}
				try {
					String tempString = fieldArr[i].getText().replaceAll("\\.", "");
					System.out.println(tempString);
					Long.parseLong(tempString);
				}
				catch (Exception e) {
					return false;
				}
			}
			return true;
		}
}

