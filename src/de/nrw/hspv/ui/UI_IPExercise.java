package de.nrw.hspv.ui;
import de.nrw.hspv.exercises.*;
import de.nrw.hspv.ui.StartAufgabenbearbeitung.Beschreibung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UI_IPExercise extends JPanel {
	
	
	//Erzeuge Frame
	public UI_IPExercise() {
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		JPanel fortfahren = new JPanel();
		fortfahren.setLayout(new BorderLayout());
//		FlowLayout flowLayout = (FlowLayout) fortfahren.getLayout();
//		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(fortfahren,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		fortfahren.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton fortButton = new JButton("Fortfahren");
		fortButton.setHorizontalAlignment(SwingConstants.RIGHT);
		fortButton.setAlignmentX(RIGHT_ALIGNMENT);
		fortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(fortfahren);
				add(new UI_IPRuntime(), BorderLayout.CENTER);
				revalidate();
				repaint();
			}
		});
		
		buttonPanel.add(fortButton,BorderLayout.SOUTH);
		fortfahren.add(new Beschreibung(), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	class Beschreibung extends JPanel{
		
		Beschreibung(){
//			setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//			
//			JLabel lblNewLabel = new JLabel("In diesem Teil k\u00F6nnen Sie f\u00FCr ihre Pr\u00FCfung im Teilmodul Grld-IT \u00FCben.");
//			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
//			add(lblNewLabel);
//			
//			JLabel lblNewLabel_1 = new JLabel("Bitte nutzen Sie f\u00FCr die Eingabe der IP-Adressen folgendes Format: xxx.xxx.xxx.xxx");
//			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
//			add(lblNewLabel_1);
//			
//			JLabel lblNewLabel_2 = new JLabel("Beispiel:");
//			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
//			add(lblNewLabel_2);
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{409, 0};
			gridBagLayout.rowHeights = new int[]{14, 14, 14, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			JLabel lblNewLabel = new JLabel("In diesem Teil k\u00F6nnen Sie f\u00FCr ihre Pr\u00FCfung im Teilmodul Grld-IT \u00FCben.");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Bitte nutzen Sie f\u00FCr die Eingabe der IP-Adressen folgendes Format: xxx.xxx.xxx.xxx");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Beispiel:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			
			
			JPanel imagePanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) imagePanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 0, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 3;
			add(imagePanel, gbc_panel);

			try {
				Image exampleImage = Toolkit.getDefaultToolkit().getImage(App.class.getResource("/de/nrw/hspv/example.jpg"));
				JLabel iconLabel = new JLabel(new ImageIcon(exampleImage));
				imagePanel.add(iconLabel);
				Border border = iconLabel.getBorder();
				Border margin = new LineBorder(Color.gray,4);
				iconLabel.setBorder(new CompoundBorder(border, margin));
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
	
	
	
	}
}

