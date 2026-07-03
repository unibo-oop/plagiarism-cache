package Gui;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import menu.MenuModel;

public class MenuItemGui {
	
	
	MenuItemGui(final String s, final MenuModel m) throws ClassNotFoundException, IOException{
	
		JFrame jf = new JFrame();
		jf.setSize(300, 200);
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JLabel jl = new JLabel(s);
		JLabel lp = new JLabel("price : "); 
		JTextArea jt = new JTextArea(Double.toString(m.getItem(s).getPrice()));
		jt.setLineWrap(true);
		jt.setEditable(true);
		jp.add(jl,FlowLayout.LEFT);
		jp.add(lp,FlowLayout.CENTER);
		jp.add(jt,FlowLayout.RIGHT);
		JButton b = new JButton("Apply Changes");
		
		b.addActionListener( e -> {
			try {
				jf.setAlwaysOnTop(false);
				String qty = jt.getText();
				qty = qty.replace(",", ".");
				m.addItem(s, Double.parseDouble(qty));
				jf.dispose();
			} catch (NumberFormatException | IOException e1) {
				
				e1.printStackTrace();
			}
		});
		jp.add(b,JPanel.BOTTOM_ALIGNMENT);
		jf.add(jp);
		jf.setAlwaysOnTop(true);
		jf.setVisible(true);
	}

}
