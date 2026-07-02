package View.cardlayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class ViewImpiegati extends JPanel {
	private static final int index = 5;
	public ViewImpiegati() {
	
		JFrame frame = new JFrame("Risto");
		frame.getContentPane().setLayout(null);
		
		JLabel lblPresent = new JLabel("present");
		lblPresent.setBounds(128, 73, 61, 14);
		frame.getContentPane().add(lblPresent);
		
		JButton btnNewButton_1 = new JButton("Num cuochi");
		btnNewButton_1.setBounds(10, 69, 108, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNumCamerieri = new JButton("Num Camerieri");
		btnNumCamerieri.setBounds(10, 250, 108, 23);
		frame.getContentPane().add(btnNumCamerieri);
		
		JLabel lblPresent_1 = new JLabel("present");
		lblPresent_1.setBounds(128, 254, 61, 14);
		frame.getContentPane().add(lblPresent_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/rsz_6401_render_lapin_cretin.png")));
		lblNewLabel.setBounds(217, 11, 191, 179);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/images/rsz_11337371484.png")));
		lblNewLabel_1.setBounds(228, 214, 180, 161);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnOpenRestaurant = new JButton("Open Restaurant");
		btnOpenRestaurant.setBounds(181, 430, 115, 23);
		frame.getContentPane().add(btnOpenRestaurant);
	}

	public static int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}
}
