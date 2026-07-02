package view;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.interfaces.IController;

/**
 * Menu to manage these operation: Model creation, saving, loading, import/export of subjects list and closing the program.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuFile extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JMenuItem itemNew = new JMenuItem("New", new ImageIcon(getClass().getResource("/new.gif")));
	private final JMenuItem itemLoad = new JMenuItem("Load", new ImageIcon(getClass().getResource("/load.gif")));
	private final JMenuItem itemSaveAs = new JMenuItem("Save as..", new ImageIcon(getClass().getResource("/save.gif")));
	private final JMenuItem itemImportSub = new JMenuItem("Import subject list", new ImageIcon(getClass().getResource("/import.gif")));
	private final JMenuItem itemExportSub = new JMenuItem("Export subject list", new ImageIcon(getClass().getResource("/export.gif")));
	private final JMenuItem itemExit = new JMenuItem("Exit");
	
	private final Frame parent;
	private IController controller;
	
	private final JFileChooser chooser = new JFileChooser();
	
	/**
	 * Menu creation.
	 * 
	 * @param f The Frame from which the menù is displayed.
	 */
	public MenuFile(final Frame f) {
		super("File");
		parent = f;
		add(itemNew);
		add(itemLoad);
		add(itemSaveAs);
		add(itemImportSub);
		add(itemExportSub);
		add(itemExit);
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
	 * Method that associates to each {@link JMenuItem} a listener based on what operation it must perform.
	 */
	private void setHandlers() {
		itemNew.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(parent, "Do you wanna save before create a new model?", "Saving?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.NO_OPTION) {
				controller.commandNew();
			}
			if (n == JOptionPane.YES_OPTION) {
				final int returnVal = chooser.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.commandSave(getPath());
					controller.commandNew();
				}
			}
		});
		
		itemLoad.addActionListener(e -> {
			final int returnVal = chooser.showOpenDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandLoad(getPath());
			}
		});
		
		itemSaveAs.addActionListener(e -> {
			final int returnVal = chooser.showSaveDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandSave(getPath());
			}
		});
		
		itemImportSub.addActionListener(e -> {
			final int returnVal = chooser.showOpenDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandImportSubjectList(getPath());
			}
		});
		
		itemExportSub.addActionListener(e -> {
			final int returnVal = chooser.showSaveDialog(parent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controller.commandExportSubjectList(getPath());
			}
		});
		
		itemExit.addActionListener(e -> {
			final int n = JOptionPane.showConfirmDialog(parent, "Do you wanna save before quit?", "Quitting..", 
														JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION) {
				final int returnVal = chooser.showSaveDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.commandSave(getPath());
					System.exit(0);
				}
			}
			if (n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		});
	}
	
	private String getPath() {
		return chooser.getCurrentDirectory() + System.getProperty("file.separator") + chooser.getSelectedFile().getName();
	}
}
