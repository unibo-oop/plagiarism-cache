package view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frameHome;
	public final JButton btnStart = new JButton("Start");
	public boolean avviato = false;
	
	/**
	 * Constructor of the class Home
	 */
	public Home() {
		initialize();
	}
	
	/**
	 * Method that implements the home of the application
	 */
	private void initialize() {
		frameHome = new JFrame();
		
		frameHome.setBounds(0,0,720,720);
		frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameHome.setResizable(false);
		frameHome.getContentPane().setLayout(null);
		
		
		btnStart.setBounds(280, 320, 111, 23);
		frameHome.getContentPane().add(btnStart);
		
		JLabel lblSfondo = new JLabel("");
		lblSfondo.setBounds(0, 0, 720, 720);
		lblSfondo.setIcon(new ImageIcon(Home.class.getResource("/res/start-jpg-iloveimg-resized.jpg")));
		frameHome.getContentPane().add(lblSfondo);
		
		btnStart.addActionListener(e ->{
			if(avviato == false) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TestForm window = new TestForm();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frameHome.dispose();
			avviato = true;
			}
		});
		
	}
	
	
	
}
