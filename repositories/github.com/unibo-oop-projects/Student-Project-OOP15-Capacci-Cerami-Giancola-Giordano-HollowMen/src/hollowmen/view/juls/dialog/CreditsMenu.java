package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code CreditsMenu} class shows all information about 
 * the programmers of the app.
 * @author Juls
 *
 */
public class CreditsMenu extends OptionDialog {

	private static final long serialVersionUID = -7385165484666077280L;
	private JLabel credits = new JLabel();
	private PaintedButton close = new PaintedButton("CLOSE");	
	private JPanel panel = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(0, 0, 680, 500)
							.addTo(credits)
							.build();
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(270, 415, 150, 58)
							.addTo(close)
							.build();


	public CreditsMenu(Frame frame) {
		super(frame);
		this.loadImages();
		this.add(panel);
		this.add(buttonC);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}

	protected void loadImages() {
		credits.setIcon(UtilitySingleton.getInstance().getStorage().get("credits"));
	}
}
