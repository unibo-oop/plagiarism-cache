package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code ExitDialog} class shows a dialog informing the user
 * that he is going to quit the app.
 */
public class ExitDialog extends MessageDialog {

	private static final long serialVersionUID = 3130816935656406102L;
	private PaintedButton no = new PaintedButton("NO");
	private PaintedButton yes = new PaintedButton("YES");
	private JPanel buttonPanel = PanelBuilder.getBuilder()
								.layout(1, 2, 40, 0)
								.bound(80, 100, 340, 58)
								.addTo(no)
								.addTo(yes)
								.build();

	
	public ExitDialog(Frame frame) {
		super(frame);
		super.addMessage("Are you sure you want to quit the game?");
		super.setTextForeground(Color.WHITE);
		
		message.setBounds(60, 50, 340, 50);
		no.addActionListener(listener);
		yes.addActionListener(listener);
		
		this.add(message);
		this.add(buttonPanel);
		setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("NO")) {
				dispose();
			} else if (name.equals("YES")) {
				System.exit(0);
			}
		}
	};
}
