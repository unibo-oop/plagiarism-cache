package view;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.interfaces.IView;
import controller.interfaces.IController;

/**
 * Menù per la gestione delle operazioni di: undo, redo, aggiunta e rimozione di una materia dalla lista delle materie
 * disponibili.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuModify extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JMenuItem itemUndo = new JMenuItem("Undo", new ImageIcon(getClass().getResource("/undo.gif")));
	private final JMenuItem itemRedo = new JMenuItem("Redo", new ImageIcon(getClass().getResource("/redo.gif")));
	private final JMenuItem itemAddSub = new JMenuItem("Add new subjcet to list");
	private final JMenuItem itemRemoveSub = new JMenuItem("Remove subjcet from list");
	
	private final AddSubjectForm addSub;
	private final ListObjectForm remSub;
	
	private final IView parent;
	private IController controller;
	
	/**
	 * Menu creation.
	 * 
	 * @param v View The view from which the menù is displayed.
	 * @param add Dialog for inserting data related to the creation of a new subject .
	 * @param rem Dialog to select the subject to delete from the list.
	 */
	public MenuModify(final IView v, final AddSubjectForm add, final ListObjectForm rem) {
		super("Modify");
		parent = v;
		addSub = add;
		remSub = rem;
		add(itemUndo);
		itemUndo.setEnabled(false);
		add(itemRedo);
		itemRedo.setEnabled(false);
		addSeparator();
		add(itemAddSub);
		add(itemRemoveSub);
		setHandlers();
	}
	
	/**
	 * Method to attach a controller at this menu.
	 * 
	 * @param ctrl Controller on which performing the operations that provides this menu.
	 */
	public void setController(final IController ctrl) {
		controller = ctrl;
	}
	
	/**
	 * Method to enable or disable undo command in the view.
	 * 
	 * @param bool if is true it will be enabled, otherwise will be disabled.
	 */
	public void setEnabledCommandUndo(final boolean bool) {
		itemUndo.setEnabled(bool);
	}

	/**
	 * Method to enable or disable redo command in the view.
	 * 
	 * @param bool if is true it will be enabled, otherwise will be disabled.
	 */
	public void setEnabledCommandRedo(final boolean bool) {
		itemRedo.setEnabled(bool);
	}
	
	/**
	 * Method that associates to each {@link JMenuItem} a listener based on what operation it must perform.
	 */
	private void setHandlers() {
		itemUndo.addActionListener(e -> {
			controller.commandUndo();
		});
		
		itemRedo.addActionListener(e -> {
			controller.commandRedo();
		});
		
		itemAddSub.addActionListener(e -> {
			addSub.setVisible(true);
		});
		
		itemRemoveSub.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				parent.commandFailed("There are no subject in the list!");
			} else {
				remSub.setList(controller.getSubjectsList(), "Subject");
				remSub.setVisible(true);
			}
		});
	}
}
