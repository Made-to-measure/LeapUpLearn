package de.nrw.hspv.ui;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartAufgabenbearbeitung extends JPanel {

	/**
	 * Create the panel.
	 */
	public StartAufgabenbearbeitung() {
		setLayout(new BorderLayout(5,5));
		JPanel fortfahren = new JPanel();
		FlowLayout flowLayout = (FlowLayout) fortfahren.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		JButton fortButton = new JButton("Fortfahren");
		fortfahren.add(fortButton);
		add(fortfahren,BorderLayout.SOUTH);
		fortButton.setAlignmentX(RIGHT_ALIGNMENT);
		add(new Beschreibung(), BorderLayout.CENTER);
		fortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mainframe.MainPanel.add(new PanelAufgabenbearbeitung());
			}
		});
	}
	
	
	class Beschreibung extends JPanel{
		
		Beschreibung() {
			setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel lblNewLabel = new JLabel("In diesem Teil k\u00F6nnen Sie f\u00FCr ihre Pr\u00FCfung im Teilmodul Grld-IT \u00FCben.");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Bitte nutzen Sie f\u00FCr die Eingabe der IP-Adressen folgendes Format: xxx.xxx.xxx.xxx");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Beispiel:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
			add(lblNewLabel_2);
		
		
		}
	}
}
	
//	public class ImagePanel extends JPanel{
//
//	    private BufferedImage image;
//
//	    public ImagePanel() {
//	       try {                
//	          image = getImage(App.class.getResource("/de/nrw/hspv/LUL.jpg"));
//	       } catch (IOException ex) {
//	            // handle exception...
//	       }
//	    }
//
//	    @Override
//	    protected void paintComponent(Graphics g) {
//	        super.paintComponent(g);
//	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
//	    }
//
//	}
//	
//	public static void main (String[] args) {
//		
//	}



