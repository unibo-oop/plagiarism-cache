package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hollowmen.view.SingletonFrame;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code NewGameDialog} class shows the user a simple message
 * in order to inform it about the imminent start of the game.
 * @author Juls
 *
 */
public class NewGameDialog extends MessageDialog {

	private static final long serialVersionUID = -8399745984113823014L;
	private PaintedButton no = new PaintedButton("NO");
	private PaintedButton yes = new PaintedButton("YES");
	private JPanel buttonPanel = PanelBuilder.getBuilder()
								.layout(1, 2, 40, 0)
								.bound(80, 100, 340, 58)
								.addTo(no)
								.addTo(yes)
								.build();
	
	public NewGameDialog(Frame frame) {
		super(frame);
		super.addMessage("<html>You are going to start a New Adventure. <br> Are you ready?<html>");
		super.setTextForeground(Color.WHITE);

		message.setBounds(60, 50, 340, 50);
		this.add(message);
		this.add(buttonPanel);
		no.addActionListener(listener);
		yes.addActionListener(listener);
		
		setVisible(true);
	}

	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("NO")) {
				dispose();
			} else if (name.equals("YES")) {
				new DifficultyMenu(SingletonFrame.getInstance());
				dispose();
			}
		}
	};
}
