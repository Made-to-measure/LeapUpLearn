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
import java.util.logging.Level;

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
import de.nrw.hspv.login.Login;

public class UI_IPRuntime extends JPanel {
	/**
	 * Panel zur Anzeige von IP-Adressaufgaben an
	 * 
	 * @param
	 * @author Jannik
	 * @version 1.0
	 */
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
//	TimePanel timepanel;
//	Stopwatch stopwatch;
	
	/**
	 * Erzeugt das Hauptlauffenster zum Bearbeiten der IP-Aufgaben
	 */
	UI_IPRuntime(){
		
		currentExercise = new IPExercise();	//erzeuge neue erste IP-Aufgabe
		fieldArr = new JTextField[8];	//erzeuge 8 Textfelder
		
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
		
		//Setze Hinweis zum Bearbeiten
		JLabel labelFormatAdvice = new JLabel("F\u00FClle die leeren Textfelder! Benutze dabei bitte folgendes Format: xxx.xxx.xxx.xxx");
		labelFormatAdvice.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(labelFormatAdvice);
		
		//Erzeuge Button unten rechts
		btnRightCorner = new JButton("\u00DCberpr\u00FCfen");
		btnRightCorner.setEnabled(false);
		btnRightCorner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page == 1) {
					showInputs();
					btnRightCorner.setText("Zeige L\u00F6sung");
					labelFormatAdvice.setText("Nun siehst du, ob deine L\u00F6sung richtig war!");
					page++;
					
				}
				else if(page ==2) {
					showResults();
					btnRightCorner.setText("N\u00E4chste Aufgabe");
					labelFormatAdvice.setText("Hier siehts du die richtigen Ergebnisse!");
					page++;
				}
				else if(page == 3) {
					loadNewExercise();
					btnRightCorner.setText("\u00DCberpr\u00FCfen");
					labelFormatAdvice.setText("F\u00FClle die leeren Textfelder! Benutze dabei bitte folgendes Format: xxx.xxx.xxx.xxx");
					page=1;
				}
			}
		});
		panel_2.add(btnRightCorner);
	//	panel_2.add(timepanel = new TimePanel());
	//	stopwatch = new Stopwatch();
	//	stopwatch.start();
	//	timepanel.setLblZeit(Double.toString(stopwatch.getElapsedMin()));
		
		add(new PanelIpZeilen(), BorderLayout.CENTER);
		loadFirstExercise();	//lade erste Aufgabe in die Panel und Felder
	}
	
	/**
	 * läd die erste Aufgabe indem die jeweiligen felder beschrieben werden
	 */
	public void loadFirstExercise() {
		boolean[] boolArr = this.currentExercise.getExerciseType();
		for(int i=0; i<8; i++) {
			if(boolArr[i]) {
				this.addressPanels[i].add(fieldArr[i] =new JTextField());
				//formatiert die einzelnen Felder in denen die angezeigten Werte stehen
				fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
				fieldArr[i].setEditable(false);
				fieldArr[i].setFocusable(false);
				fieldArr[i].setBackground(Color.LIGHT_GRAY);
				fieldArr[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			}
			else { //sonst erstelle ein leeres Textfeld
				this.addressPanels[i].add(fieldArr[i] = new JTextField());	
			}
			fieldArr[i].getDocument().addDocumentListener(new DocumentListener() {
				//registiriert Veraenderungen in den Textfeldern  
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
		App.logger.log(Level.INFO, "Erste IPAufgabe geladen");
	}
	
	/**
	 * entscheidet ob der Button zur Aufgabensteuerung enabled wird 
	 */
	private void statusRightButton() {
		btnRightCorner.setEnabled(checkFields());
	}
	
	/**
	 * laed neue Aufgabe
	 */
	public void loadNewExercise() {
		this.currentExercise = new IPExercise();	//ändere die momentane Aufgabe
		boolean[] boolArr = this.currentExercise.getExerciseType();	//hole den neuen Aufgabentyp
		for(int i=0; i<8; i++) {
			//formatiere alle Felder auf Standard
			fieldArr[i].setBackground(Color.WHITE);
			fieldArr[i].setFont(null);
			fieldArr[i].setEditable(true);
			fieldArr[i].setFocusable(true);
			if(boolArr[i]) {
				//Zeige neue sichtbaren Werte der Aufgabe an
				fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
				fieldArr[i].setEditable(false);
				fieldArr[i].setFocusable(false);
				fieldArr[i].setBackground(Color.LIGHT_GRAY);
				fieldArr[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			}
			else {
				//ansonsten clear das Textfeld
				fieldArr[i].setText("");
			}
		}
		App.logger.log(Level.INFO, "Neue IPAufgabe geladen");
	}
	
	/**
	 * Fragt alle Textfelder ab
	 * @return Stringarray mit den Eingaben aus den Textfeldern
	 */
	public String[] getInputs() {
		String[] tempArr = new String[fieldArr.length];
		for(int i=0; i<tempArr.length; i++) {
			tempArr[i] = fieldArr[i].getText(); //Frage Text ab
			tempArr[i] = tempArr[i].trim();	//schneide leerzeichen vorn/hinten ab
		}
		return tempArr;
	}
	
	/**
	 * Methode welche die Eingaben anzeigt und die Textfelder nach Richtigkeit einfaerbt
	 */
	public void showInputs() {
		this.results = this.currentExercise.validateInputs(getInputs()); //validiere die Eingaben
		for(int i = 0; i < results.length; i++) {
			if(!this.currentExercise.getExerciseType()[i]) {
				//setze alle Felder auf uneditierbar
				fieldArr[i].setEditable(false);
				if(results[i]) {
					fieldArr[i].setBackground(lightGreen);
				}
				else {
					fieldArr[i].setBackground(lightRed);
					currentExercise.geloest = false;
				}
			}
		}
		currentExercise.addEintrag(Login.aktiverUser, currentExercise);
		App.logger.log(Level.INFO, "Eingabeberpr\u00fcfung angezeigt");
	}
	
	/**
	 * Methode zeigt die Richtigen Werte an
	 */
	public void showResults() {
		for(int i = 0; i< fieldArr.length; i++) {
			if(!this.currentExercise.getExerciseType()[i]) {
				if(i == 1 && fieldArr[i].getBackground() == lightRed) {
					//falls die Hostadresse falsch ist sage, dass die Eingabe ausserhalb des Hostbereichs liegt
					fieldArr[i].setText(fieldArr[i].getText() + " liegt au\u00DFerhalb des Hostbereichs");
					fieldArr[i].setBackground(new Color(255,102,102)); //light red
				}
				else{
					if(i != 1) {
						//zeige die generierten Adressen an
						fieldArr[i].setText(currentExercise.getStringFormatByNumber(i));
					}
				}
			}	 
		}
		App.logger.log(Level.INFO, "L\u00f6sungen geladen");
	}
	
	/**
	 * Mehode prueft, ob die Felder mit gueltigen Eingaben ausgefuellt werden
	 * @return Wahrheitswert ob alle Felder gueltig ausgefuellt sind
	 */
	public boolean checkFields() {
		String[] inputArr = getInputs();
		if(page != 2) {
			for(int i = 0; i<inputArr.length; i++) {
				if(inputArr[i].length() <= 0) {
					return false;
				}
				try {
					String tempString = inputArr[i].replaceAll("\\.", ""); //formatiere so, dass alle Punkte geloescht werden
					//System.out.println(tempString);
					if(i == 7) { //Wenn es um die Binärdarstellung der CIDR-Klasse geht muss etwas anders geprüft werden
						for(int j = 0; j < tempString.length(); j++) {
							Integer.valueOf(tempString.charAt(j));
						}
					}
					else {
						Long.parseLong(tempString);
						//solange der String nicht parsable ist werfe eine Exception
					}
				}
				catch (NumberFormatException e) {
					//e.printStackTrace();
					//ist der String nicht parsable soll der Button nicht aktiviert werden
					return false;	
				}
			}
		}
		App.logger.log(Level.INFO, "Alle Felder ausgef\u00fcllt");
		return true;
	}
	
	class PanelIpZeilen extends JPanel{
	/**
	 * Panel welches Die jeweiligen Felder in einem Springlayout anlegt
	 */
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
			JLabel label2 = new JLabel("ausgew\u00E4hlte Hostadresse");
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
			JLabel label8 = new JLabel("CIDR-Bin\u00E4r");
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
}

