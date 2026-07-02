package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.InputMenu;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.ArrowButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.buttons.TranslucentButton.Direction;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code HelpMenu} shows the commands of the game. Extends {@link OptionDialog}.
 * 
 * @author Juls
 */
public class HelpMenu extends OptionDialog {

	private static final long serialVersionUID = 8558594124420531160L;
	private final JLabel title = new JLabel();
	private final JLabel sheet1 = new JLabel();
	private final JLabel sheet2 = new JLabel();
	private ArrowButton left = new ArrowButton(Direction.LEFT);
	private ArrowButton right = new ArrowButton(Direction.RIGHT);
	private PaintedButton close = new PaintedButton("CLOSE");
	
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(240, 430, 150, 58)
							.addTo(close)
							.build();
	private JPanel panelSheet1 = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(150, 80, 350, 350)
							.addTo(sheet1)
							.build();
	private JPanel arrowC1 = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(500, 430, 40, 40)
							.addTo(right)
							.build();
	private JPanel  panelSheet2 = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(150, 80, 350, 350)
							.addTo(sheet2)
							.build();
	private JPanel arrowC2 = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(500, 435, 40, 40)
							.addTo(left)
							.build();
	
	
	public HelpMenu(Frame frame) {
		super(frame);
		this.loadImages();
				
		title.setBounds(250, 15, 145, 70);
		this.add(title);
		this.add(buttonC);	
		this.add(panelSheet1);
		this.add(arrowC1);

		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			}		
		});		
		
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(panelSheet1);
				add(panelSheet2);
				remove(arrowC1);
				add(arrowC2);
				repaint();
		        revalidate();
			}
		});
		
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(panelSheet2);
				add(panelSheet1);
				remove(arrowC2);
				add(arrowC1);
				repaint();
		        revalidate();
			}
		});
		
		setVisible(true);
	}
	
	@Override
	protected void loadImages() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("help"));
		sheet1.setIcon(UtilitySingleton.getInstance().getStorage().get("helpSheet1"));
		sheet2.setIcon(UtilitySingleton.getInstance().getStorage().get("helpSheet2"));
	}
}
