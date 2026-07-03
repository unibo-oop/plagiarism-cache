/*In questa GUI è semplicemente possibile aggiungere/rimuovere piatti dal menu dell'hotel
 * 
 */
package vieww;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import model.Hotel;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuView.
 */
public class MenuView extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tyoe of meals. */
	private final Choice choice = new Choice();
	
	/** The model for JList. */
	private final DefaultListModel<String> model;
	
	/** The list. */
	private final JList<String> list;
	
	/** The buttons panel. */
	JPanel buttonPanel = new JPanel();

	/**
	 * Instantiates a new menu view.
	 */
	MenuView() {
		super();
		this.getContentPane().setLayout(new BorderLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(1000, 500));
		this.setLayout(new FlowLayout());
		model = new DefaultListModel<>();
		choice.add("Antipasti");
		choice.add("Primi");
		choice.add("Secondi");
		choice.add("Dessert");
		list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(100,100));
		JButton addButton = new JButton("Aggiungi piatto");
		JButton removeButton = new JButton("Elimina piatto");
		JButton removeAll = new JButton("Rimuovi tutto");
		JLabel label = new JLabel("Scegli tipo piatto: ");
		this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		if (Hotel.getInstance().getDailyMenu().getMealList("Antipasti") != null) {
			for (String s : Hotel.getInstance().getDailyMenu().getMealList("Antipasti")) {
				model.addElement(s);
			}
		}
		buttonPanel.add(label);
		buttonPanel.add(choice);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(removeAll);
		this.add(pane);
		choice.addItemListener(e -> {
			model.clear();
			if (Hotel.getInstance().getDailyMenu().getMealList(choice.getSelectedItem()) != null) {
				for (String s : Hotel.getInstance().getDailyMenu().getMealList(choice.getSelectedItem())) {
					model.addElement(s);
				}
			}
		});
		addButton.addActionListener(e -> {
			String response = JOptionPane.showInputDialog(null, "Inserimento", "Inserisci il nome del piatto",
					JOptionPane.QUESTION_MESSAGE);
			Controller.getInstance().addMenuVoice(choice.getSelectedItem(), response);
			model.addElement(response);
			update(choice.getSelectedItem());
			
		});

		/*
		 * JPanel adding = new JPanel(); adding.setLayout(new
		 * BoxLayout(adding,BoxLayout.Y_AXIS)); adding.add(new JLabel(
		 * "Inserisci il nome del nuovo piatto: ")); JTextField text = new
		 * JTextField(); adding.add(text); JButton confirm = new
		 * JButton("Conferma"); adding.add(confirm);
		 * this.add(adding,BorderLayout.SOUTH); adding.setVisible(true);
		 * confirm.addActionListener(gatto ->{ this.setVisible(true);
		 * DailyMenu.getInstance().addPiatto(choice.getSelectedItem(),text.
		 * getText()); update(choice.getSelectedItem()); text.setText(null); });
		 */

		removeButton.addActionListener(e -> {

			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex != -1) {
				Hotel.getInstance().getDailyMenu().removePiatto(choice.getSelectedItem(), model.get(selectedIndex));
				model.remove(selectedIndex);
				update(choice.getSelectedItem());
			}

		});

		removeAll.addActionListener(e -> {
			Hotel.getInstance().getDailyMenu().cleanAllandUpdate();
			this.update(choice.getSelectedItem());

		});
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Update.
	 *
	 * @param s the s
	 */
	public void update(String s) {
		model.clear();
		if (Hotel.getInstance().getDailyMenu().getMealList(s) != null) {
			for (String st : Hotel.getInstance().getDailyMenu().getMealList(s)) {
				model.addElement(st);
			}
		}

	}

}
