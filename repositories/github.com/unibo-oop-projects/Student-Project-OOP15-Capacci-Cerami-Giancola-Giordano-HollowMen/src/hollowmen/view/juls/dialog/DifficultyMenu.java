package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.view.UtilitySingleton;
import hollowmen.enumerators.Difficulty;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code DifficultyMenu} class allows the user
 * to choose the game difficulty.
 * 
 * @author Juls
 */
public class DifficultyMenu  extends MessageDialog {

	private static final long serialVersionUID = -8695951773300972039L;	
	private JLabel title = new JLabel();
	private JLabel selection = new JLabel();
	private String choice;
	private PaintedButton easy = new PaintedButton("EASY");
	private PaintedButton normal = new PaintedButton("NORMAL");
	private PaintedButton hard = new PaintedButton("HARD");
	private PaintedButton confirm = new PaintedButton("CONFIRM");
	private JPanel buttonsC = PanelBuilder.getBuilder()
							.layout(1, 3, 10, 0)
							.bound(15, 110, 470, 58)
							.addTo(easy)
							.addTo(normal)
							.addTo(hard)
							.build();
	private JPanel confirmC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(175, 230, 150, 58)
							.addTo(confirm)
							.build();

	public DifficultyMenu(Frame frame) {
		super(frame);
		this.setSize(500, 300);	
		this.loadImage();
		
		title.setBounds(115, 30, 270, 70);
		selection.setBounds(228, 180, 100, 40);
		selection.setForeground(Color.WHITE);
		
		this.add(buttonsC);
		this.add(confirmC);
		this.add(title);
		this.add(selection);
		this.addToList();
		this.addMouseListener(dialogL);
		confirm.setEnabled(false);
		
		this.setVisible(true);
	}

	/**
	 * This method add a listener to the buttons.
	 */
	private void addToList() {
		List<PaintedButton> buttonList = new ArrayList<>();
		
		buttonList.add(easy);
		buttonList.add(normal);
		buttonList.add(hard);
		buttonList.add(confirm);
		
		for(PaintedButton b : buttonList) {
			b.addActionListener(listener);
		}
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("EASY") || name.equals("NORMAL") || name.equals("HARD")) {
				confirm.setEnabled(true);
				selection.setText(name);
				choice = name;
			} else if (name.equals("CONFIRM")) {
				UtilitySingleton.getInstance().getObserver().addInput(Difficulty.valueOf(choice));
				dispose();	
				
			}
		}
	};
	
	MouseListener dialogL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			selection.setText("");
			confirm.setEnabled(false);
		}
	};
	
	private void loadImage() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("difficulty"));
	}

}
