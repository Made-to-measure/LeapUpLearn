package de.nrw.hspv.ui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;
import de.nrw.hspv.statistics.zuStatistik;

/**
 * Panel zur Anzeige von CPU/Speicher-Zugriffszeiten
 * 
 * @author Christian
 * @version 1.0
 *
 */
public class UI_CPUTakt extends JPanel implements zuStatistik{
	//Steuerelemente erstellen
	private final JLabel lblAufgabe= new JLabel("Machen Sie die folgenden Werte vergleichbar, wenn die CPU mit 2GHz getaktet ist");
	private JButton btnPruefen = new JButton("Pr�fen");
	private JTextField txtL1Zeit;
	private JTextField txtL3Zeit;
	private JTextField txtL3VerglW;
	private JTextField txtL2Zeit;
	private JTextField txtL2VerglW;
	private JLabel lblZugrZeit;
	private JTextField txtRegAccZeit;
	private JTextField txtLocDRAMAccVerglW;
	private JTextField txtLocDRAMAccZeit;
	private JTextField txtRegAccVerglW;
	private JLabel lblVergleichbareWerte;
	private JTextField txtL1VerglW;
	private JLabel lblSpeicher;
	private JLabel lblRegAccess;
	private JLabel lblLocalDRAMAccess;
	private JLabel lblL3CacheHit;
	private JLabel lblL2CacheHit;
	private JLabel lblL1CacheHit;


	/**
	 * Panel zur Anzeige der Aufgabe CPU/Speicher-Zugriffszeiten
	 */
	public UI_CPUTakt() {

		setLayout(new BorderLayout(0, 0));
		
		//Zusaetzliches Panel um Lbl mit Aufgabenstellung aufzunehmen
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH); //Anzeige der Aufg im oberen Bereich
		panel.add(lblAufgabe);
		
		//Zusaetzliches Panel um Btn zur Ueberpruefung der eingegebenen Werte
		JPanel panelS = new JPanel();
		add(panelS, BorderLayout.SOUTH); //Btn unter der Aufgabe platzieren
		panelS.add(btnPruefen);
		
		//Funktion des Ueberpruefen-Btn:
		btnPruefen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckErgebnis();
				
			}
		});
		
		//Panel mit einer Aufgabe als "Tabelle" im "Klausur"-Stil
		JPanel panelAufg = new JPanel();
		add(panelAufg, BorderLayout.CENTER); //Anzeige in der Mitte des UI_CPUTaktPanels
		
		//rechts etwas Platz lassen f�r die Optik
		Component horizontalStrut = Box.createHorizontalStrut(50);
		add(horizontalStrut, BorderLayout.EAST);
		
		//Layout setzen
		panelAufg.setLayout(new GridLayout(6, 3, 0, 0)); //6 Zeilen, 3 Spalten
		
		//Spalten-�berschriften
		lblSpeicher = new JLabel("Speicher  ");
		lblSpeicher.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblSpeicher);
		
		lblZugrZeit = new JLabel("Zugriffszeit (cycles)");
		lblZugrZeit.setHorizontalAlignment(SwingConstants.CENTER);
		panelAufg.add(lblZugrZeit);
		
		lblVergleichbareWerte = new JLabel("Vergleichbare Werte (ns)");
		lblVergleichbareWerte.setHorizontalAlignment(SwingConstants.CENTER);
		panelAufg.add(lblVergleichbareWerte);
		
		//Zeile "Register-Access"
		lblRegAccess = new JLabel("Register Access  ");
		lblRegAccess.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblRegAccess);
		
		txtRegAccZeit = new JTextField();
		txtRegAccZeit.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegAccZeit.setText("1");
		//Vorgabewert: Steuerelement deaktivieren
		txtRegAccZeit.setEditable(false);
		txtRegAccZeit.setFocusable(false);
		panelAufg.add(txtRegAccZeit);
		txtRegAccZeit.setColumns(10);
		
		txtRegAccVerglW = new JTextField();
		txtRegAccVerglW.setHorizontalAlignment(SwingConstants.CENTER);
		//txtRegAccVerglW.setText("0,5");
		panelAufg.add(txtRegAccVerglW);
		txtRegAccVerglW.setColumns(10);
		
		//Zeile "Local DRAM Access"
		lblLocalDRAMAccess = new JLabel("Local DRAM Access  ");
		lblLocalDRAMAccess.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblLocalDRAMAccess);
		
		txtLocDRAMAccZeit = new JTextField();
		txtLocDRAMAccZeit.setHorizontalAlignment(SwingConstants.CENTER);
		//txtLocDRAMAccZeit.setText("25");
		panelAufg.add(txtLocDRAMAccZeit);
		txtLocDRAMAccZeit.setColumns(10);
		
		txtLocDRAMAccVerglW = new JTextField();
		txtLocDRAMAccVerglW.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocDRAMAccVerglW.setText("50");
		//Vorgabewert: Steuerelement deaktivieren
		txtLocDRAMAccVerglW.setEditable(false);
		txtLocDRAMAccVerglW.setFocusable(false);
		panelAufg.add(txtLocDRAMAccVerglW);
		txtLocDRAMAccVerglW.setColumns(10);
		
		//Zeile Level 3 Cache
		lblL3CacheHit = new JLabel("Level 3 Cache Hit  ");
		lblL3CacheHit.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblL3CacheHit);
		
		txtL3Zeit = new JTextField();
		txtL3Zeit.setHorizontalAlignment(SwingConstants.CENTER);
		txtL3Zeit.setText("60");
		//Vorgabewert: Steuerelement deaktivieren
		txtL3Zeit.setEditable(false);
		txtL3Zeit.setFocusable(false);
		panelAufg.add(txtL3Zeit);
		txtL3Zeit.setColumns(10);
		
		txtL3VerglW = new JTextField();
		txtL3VerglW.setHorizontalAlignment(SwingConstants.CENTER);
		//txtL3VerglW.setText("30");
		panelAufg.add(txtL3VerglW);
		txtL3VerglW.setColumns(10);
		
		//Zeile Level 2 Cache
		lblL2CacheHit = new JLabel("Level 2 Cache Hit  ");
		lblL2CacheHit.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblL2CacheHit);
		
		txtL2Zeit = new JTextField();
		txtL2Zeit.setHorizontalAlignment(SwingConstants.CENTER);
		txtL2Zeit.setText("12");
		panelAufg.add(txtL2Zeit);
		txtL2Zeit.setColumns(10);
		//Vorgabewert: Steuerelement deaktivieren
		txtL2Zeit.setEditable(false);
		txtL2Zeit.setFocusable(false);
		
		txtL2VerglW = new JTextField();
		txtL2VerglW.setHorizontalAlignment(SwingConstants.CENTER);
		//txtL2VerglW.setText("6");
		panelAufg.add(txtL2VerglW);
		txtL2VerglW.setColumns(10);
		
		//Zeile Level 1 Cache
		lblL1CacheHit = new JLabel("Level 1 Cache Hit  ");
		lblL1CacheHit.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAufg.add(lblL1CacheHit);
		
		txtL1Zeit = new JTextField();
		txtL1Zeit.setHorizontalAlignment(SwingConstants.CENTER);
		//txtL1Zeit.setText("3");
		panelAufg.add(txtL1Zeit);
		txtL1Zeit.setColumns(10);
		
		txtL1VerglW = new JTextField();
		txtL1VerglW.setHorizontalAlignment(SwingConstants.CENTER);
		txtL1VerglW.setText("1,5");
		txtL1VerglW.setEditable(false);
		txtL1VerglW.setFocusable(false);
		panelAufg.add(txtL1VerglW);
		txtL1VerglW.setColumns(10);
		
		App.logger.log(Level.INFO, "UI CPU-Takt/Zugriffszeit erstellt");
	}
	/**
	 * Methode gleicht die eingegebenen Werte mit den erwarteten ab.
	 * <br><br>
	 *  txt-Felder werden gr�n eingef�rbt, wenn der Wert richtig ist
	 *  und rot wenn der Wert nicht der erwartete ist.
	 *
	 * @return
	 */
	public boolean CheckErgebnis() {
		if (txtRegAccVerglW.getText().matches("0,5")) {
			txtRegAccVerglW.setBackground(Color.green);
		}
		else {
			txtRegAccVerglW.setBackground(Color.RED);
		}
		if (txtLocDRAMAccZeit.getText().matches("25")) {
			txtLocDRAMAccZeit.setBackground(Color.green);
		}
		else {
			txtLocDRAMAccZeit.setBackground(Color.RED);
		}
		if (txtL3VerglW.getText().matches("30")) {
			txtL3VerglW.setBackground(Color.green);
		}
		else  {
			txtL3VerglW.setBackground(Color.RED);	
		}
		if (txtL2VerglW.getText().matches("6")) {
			txtL2VerglW.setBackground(Color.green);
		}
		else  {
			txtL2VerglW.setBackground(Color.RED);	
		}	
		if (txtL1Zeit.getText().matches("3")) {
			txtL1Zeit.setBackground(Color.green);
		}
		else {
			txtL1Zeit.setBackground(Color.RED);	
		}	
		App.logger.log(Level.INFO, "CPU-Takt / Zugriffszeitaufgabe gepr�ft");
		
		return true;
		
	}
	@Override
	public void addEintrag(User user, Exercise aufgabe) {
		// TODO Auto-generated method stub
		
	}

}
