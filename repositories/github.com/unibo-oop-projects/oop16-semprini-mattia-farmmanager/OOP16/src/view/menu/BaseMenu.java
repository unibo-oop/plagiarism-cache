package view.menu;


import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Controller;

public class BaseMenu extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseMenu(){
		super("File");
		JMenuItem save = new JMenuItem("Salva");
		JMenuItem load = new JMenuItem("Carica");
		save.addActionListener(e->{
			Controller.getController().saveData();
			JOptionPane.showMessageDialog(this, "Salvataggio avvenuto con successo!");
		});
		load.addActionListener(e->{
			Controller.getController().loadData();
			JOptionPane.showMessageDialog(this, "Caricamento avvenuto con successo!");
		});
		this.add(save);
		this.add(load);
	}

}
