package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Controller.ControllerWorkers;

/**
 * This class implements ActionListener. It is use for cancel Button and allows
 * to generate new FrameCancel if there is any reservation. If there is no items
 * do not allow to open the frame, and open the JOptionPane with the specific
 * message.
 * 
 * @author Galya Genova
 * 
 *         Modify by Massimiliano Micca
 *
 */
public class MyListenerCancel implements ActionListener {
	MainGUI mainGUI;

	public MyListenerCancel(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ControllerWorkers cntr = new ControllerWorkers();
		try {
			cntr.isEmpty();
			new FrameCancel(mainGUI);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}
}
