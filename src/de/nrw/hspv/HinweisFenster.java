package de.nrw.hspv;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HinweisFenster extends JFrame {
	
	HinweisFenster(String text) {
		super("Achtung");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg")));
		setLayout(new BorderLayout());
		
		JPanel panelOben = new JPanel();
		panelOben.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel hinweis = new JLabel(text);
		panelOben.add(hinweis);
		add(panelOben, BorderLayout.CENTER);
		
		JPanel panelUnten = new JPanel();
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HinweisFenster.super.dispose();
			}
			
		});
		panelUnten.add(ok);
		add(panelUnten, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setLocation(((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-this.getWidth())/2, ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-this.getHeight())/2);
	}
	
	public static void main(String[] args) {
		new HinweisFenster("Es ist ein Fehler aufgetreten!");
	}

}
