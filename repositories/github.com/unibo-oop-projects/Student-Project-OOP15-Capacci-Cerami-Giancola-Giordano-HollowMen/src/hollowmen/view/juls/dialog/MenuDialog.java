package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.UIManager;

import hollowmen.view.SingletonFrame;
import hollowmen.view.UtilitySingleton;
/**
 * The {@code MenuDialog} abstract class defines the preferences of 
 * some menus, such as Inventory, Skill Tree, Shop, Bestiary and Achievements.
 * @author Juls
 *
 */
public abstract class MenuDialog extends CustomDialog {

	private static final long serialVersionUID = 1935614271807195916L;
	protected JLabel title = new JLabel();

	public MenuDialog(Frame frame) {
		super(frame);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferences();
		this.addBackground();
	}
	
	public void setPreferences() {
		this.setSize(750, 550);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(SingletonFrame.getInstance());
		this.setModal(false);
		this.addBackground();
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		UIManager.put("TabbedPane.contentOpaque", false);
	}
	
	@Override
	protected void addBackground() {
		background.setIcon(UtilitySingleton.getInstance().getStorage().get("pergamena"));
		this.setContentPane(background);	
	}
	
	/**
	 * The {@code addTitle} method adds title image to the menu.
	 */
	protected void addTitle(JLabel title) {
		title.setBounds(50, 30, 210, 60);
		this.add(title);
	}

}
