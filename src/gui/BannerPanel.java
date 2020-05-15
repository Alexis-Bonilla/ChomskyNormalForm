package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class BannerPanel extends JPanel {
	public static final String IMAGE_ROUTE = "./images/banner2.png";
	
	private MainWindow main;
	private JLabel imageLab;
	
	public BannerPanel(MainWindow mainWindow) {
		Border border = BorderFactory.createTitledBorder("Aristi best teacher of the World");
		this.setBorder(border);
		main = mainWindow;
		imageLab = new JLabel();
		imageLab.setHorizontalAlignment(SwingConstants.CENTER);
		imageLab.setVerticalAlignment(SwingConstants.CENTER);
		imageLab.setIcon(new ImageIcon(IMAGE_ROUTE));
		this.setPreferredSize(new Dimension(330,250));
		this.setLayout(new BorderLayout());
		this.add(imageLab,BorderLayout.CENTER);
	}

}
