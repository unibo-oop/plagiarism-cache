package view.implementations;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameSettings {

	public FrameSettings(JFrame frame, int width, int height, boolean resizable) {
		frame.setSize(width, height);
		frame.setResizable(resizable);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (frame.getWidth() / 2);
		int yPos = (dim.height / 2) - (frame.getHeight() / 2);

		frame.setLocation(xPos, yPos);
	}
	
}
