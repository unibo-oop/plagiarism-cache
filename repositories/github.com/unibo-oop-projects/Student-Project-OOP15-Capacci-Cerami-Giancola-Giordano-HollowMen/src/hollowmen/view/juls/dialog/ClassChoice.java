package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.ClassType;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code ClassChoice} class draws on screen the menu needed
 * to choose the player class.
 * @author Juls
 *
 */
public class ClassChoice extends OptionDialog {

	private static final long serialVersionUID = 402941805788593572L;
	private ImageIcon w = UtilitySingleton.getInstance().getStorage().get("hero");
	private ImageIcon m = UtilitySingleton.getInstance().getStorage().get("mage");
	private ImageIcon a = UtilitySingleton.getInstance().getStorage().get("assassin");
	private JLabel title = new JLabel();
	private IconButton warrior = new IconButton("WARRIOR", w);
	private IconButton mage = new IconButton("MAGE", m);
	private IconButton assassin = new IconButton("ASSASSIN", a);
	private PaintedButton close = new PaintedButton("CLOSE");
	private PaintedButton select = new PaintedButton("SELECT");
	private String classSelected;
	private JPanel classC = PanelBuilder.getBuilder()
							.layout(1, 3, 50, 0)
							.bound(100, 160, 450, 200)
							.addTo(warrior)
							.addTo(mage)
							.addTo(assassin)
							.build();
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 2, 120, 0)
							.bound(100, 400, 420, 58)
							.addTo(close)
							.addTo(select)
							.build();

	
	public ClassChoice(Frame frame) {
		super(frame);
		this.loadImages();
		add(title);
		title.setBounds(50, 30, 300, 60);
		this.add(classC);
		this.add(buttonC);
		close.addActionListener(listener);
		select.addActionListener(listener);
		warrior.addActionListener(classL);
		mage.addActionListener(classL);
		assassin.addActionListener(classL);
		
		select.setEnabled(false);
		mage.setEnabled(false);
		assassin.setEnabled(false);
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				select.setEnabled(false);
			}
		});
		
		this.setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("SELECT")) {
				UtilitySingleton.getInstance().getObserver().addInput(ClassType.valueOf(classSelected));
				dispose();
			} else {
				dispose();
			}
		}
	};
	
	ActionListener classL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			select.setEnabled(true);
			classSelected = name;
		}
	};

	@Override
	protected void loadImages() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("class"));
	}

}
