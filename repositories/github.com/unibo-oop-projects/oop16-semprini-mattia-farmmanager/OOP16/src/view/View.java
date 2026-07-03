package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import controller.Controller;
import view.menu.BaseMenu;

public class View extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menu = new JMenuBar();
	private BaseMenu file = new BaseMenu();
	
	public View(){
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Farm Management");
		this.setLayout(new BorderLayout());
		menu.add(file);
		this.getContentPane().add(menu,BorderLayout.NORTH);
		this.getContentPane().add(new NavigationPanel(this), BorderLayout.CENTER);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Object[] option = {"SI","NO"};
				int i = JOptionPane.showOptionDialog(null, "Vuoi salvare prima di uscire?", "Salvataggio dati",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
				if (i==0){
					Controller.getController().saveData();
				}
				super.windowClosing(e);
			}
		});
	}
	

}
