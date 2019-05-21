package principal.ventanas;

import interfaz.bar.ProgressBar;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		
		Color trans = new Color(0,0,0,0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setUndecorated(true);
		
		JLabel label = new JLabel("");
		
		try {
			BufferedImage image = ImageIO.read(ClassLoader.class.getResourceAsStream("/screen.png"));
			ImageIcon icon = new ImageIcon(image);
			
			label.setBounds(0, 0, image.getWidth(), image.getHeight());
			setBounds(0, 0, image.getWidth(), image.getHeight());
			label.setIcon(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(label);
		
		label.setBackground(trans);
		contentPane.setBackground(trans);
		
		progressBar = new ProgressBar(0,10);
		progressBar.setBounds(0, label.getHeight()-2, label.getWidth(), 5);
		contentPane.add(progressBar);
		
		contentPane.setSize(label.getWidth(), label.getHeight()+10);
		setSize(label.getWidth()+10, label.getHeight()+10);
		setBackground(trans);
		
		setVisible(true);
	}
	
	public void subirBarra(){
		progressBar.setValue(progressBar.getValue()+1);
	}

}
