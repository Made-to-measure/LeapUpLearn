package de.nrw.hspv.ui;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import de.nrw.hspv.exercises.Exercise;
import de.nrw.hspv.login.User;
import de.nrw.hspv.statistics.zuStatistik;

public class UI_CPUTakt extends JPanel implements zuStatistik{
	private final JLabel lblAufgabe= new JLabel("Machen Sie die folgenden Werte vergleichbar, wenn die CPU mit 2GHz getaktet ist");
	private JButton btnPrüfen = new JButton("Prüfen");
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


	public UI_CPUTakt() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.add(lblAufgabe);
		JPanel panelS = new JPanel();
		add(panelS, BorderLayout.SOUTH);
		panelS.add(btnPrüfen);
		
		btnPrüfen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckErgebnis();
				
			}
		});
		
		JPanel panelAufg = new JPanel();
		add(panelAufg, BorderLayout.CENTER);
		panelAufg.setLayout(new GridLayout(6, 3, 0, 0));
		
		lblSpeicher = new JLabel("Speicher");
		panelAufg.add(lblSpeicher);
		
		lblZugrZeit = new JLabel("Zugriffszeit (cycles)");
		panelAufg.add(lblZugrZeit);
		
		lblVergleichbareWerte = new JLabel("Vergleichbare Werte (ns)");
		panelAufg.add(lblVergleichbareWerte);
		
		lblRegAccess = new JLabel("Register Access");
		panelAufg.add(lblRegAccess);
		
		txtRegAccZeit = new JTextField();
		txtRegAccZeit.setText("1");
		panelAufg.add(txtRegAccZeit);
		txtRegAccZeit.setColumns(10);
		
		txtRegAccVerglW = new JTextField();
		
		//txtRegAccVerglW.setText("0,5");
		panelAufg.add(txtRegAccVerglW);
		txtRegAccVerglW.setColumns(10);
		
		lblLocalDRAMAccess = new JLabel("Local DRAM Access");
		panelAufg.add(lblLocalDRAMAccess);
		
		txtLocDRAMAccZeit = new JTextField();
		//txtLocDRAMAccZeit.setText("25");
		panelAufg.add(txtLocDRAMAccZeit);
		txtLocDRAMAccZeit.setColumns(10);
		
		txtLocDRAMAccVerglW = new JTextField();
		txtLocDRAMAccVerglW.setText("50");
		panelAufg.add(txtLocDRAMAccVerglW);
		txtLocDRAMAccVerglW.setColumns(10);
		
		lblL3CacheHit = new JLabel("Level 3 Cache Hit");
		panelAufg.add(lblL3CacheHit);
		
		txtL3Zeit = new JTextField();
		txtL3Zeit.setText("60");
		panelAufg.add(txtL3Zeit);
		txtL3Zeit.setColumns(10);
		
		txtL3VerglW = new JTextField();
		//txtL3VerglW.setText("30");
		panelAufg.add(txtL3VerglW);
		txtL3VerglW.setColumns(10);
		
		lblL2CacheHit = new JLabel("Level 2 Cache Hit");
		panelAufg.add(lblL2CacheHit);
		
		txtL2Zeit = new JTextField();
		txtL2Zeit.setText("12");
		panelAufg.add(txtL2Zeit);
		txtL2Zeit.setColumns(10);
		
		txtL2VerglW = new JTextField();
		//txtL2VerglW.setText("6");
		panelAufg.add(txtL2VerglW);
		txtL2VerglW.setColumns(10);
		
		lblL1CacheHit = new JLabel("Level 1 Cache Hit");
		panelAufg.add(lblL1CacheHit);
		
		txtL1Zeit = new JTextField();
		//txtL1Zeit.setText("3");
		panelAufg.add(txtL1Zeit);
		txtL1Zeit.setColumns(10);
		
		txtL1VerglW = new JTextField();
		txtL1VerglW.setText("1,5");
		panelAufg.add(txtL1VerglW);
		txtL1VerglW.setColumns(10);
		
	}
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
		return true;
	}
	@Override
	public void addEintrag(User user, Exercise aufgabe) {
		// TODO Auto-generated method stub
		
	}

}
