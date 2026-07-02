package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.InputMenu;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.MainMenu;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code PauseMenu} class pauses the game. It allows to go back to
 * the Lobby or to the MainMenu.
 * @author Juls
 *
 */
public class PauseMenu extends MessageDialog {

	private static final long serialVersionUID = -4193668569929345701L;
	private JLabel title = new JLabel();
	private PaintedButton lobby = new PaintedButton("TO LOBBY");
	private PaintedButton main = new PaintedButton("TO MAIN");
	private PaintedButton resume = new PaintedButton("RESUME");
	private JPanel buttonContainer = PanelBuilder.getBuilder()
									.layout(1, 3, 10, 0)
									.bound(15, 110, 470, 58)
									.addTo(lobby)
									.addTo(main)
									.addTo(resume)
									.build();

	public PauseMenu(Frame frame) {
		super(frame);
		loadImage();
		
		title.setBounds(115, 30, 270, 70);
		this.add(title);
		this.add(buttonContainer);
		
		main.addActionListener(listener);
		resume.addActionListener(listener);
		lobby.addActionListener(listener);
		
		setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("TO MAIN")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.MAIN);
				dispose();
				new MainMenu();
			} else if (name.equals("RESUME")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			} else {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.LOBBY);
				dispose();
			}
		}
	};
	
	private void loadImage() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("pauseMenu"));
	}

}
