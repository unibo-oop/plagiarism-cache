package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import controller.ModelParam;

public class AddPhytosanitaryDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel editPanel;
	private JTextField phytoName;
	private JTextField phytoType;
	private JTextField days;
	private JTextArea phytoDescription;
	private JButton addbtn;

	public AddPhytosanitaryDialog(AddTreatmentDialog prev) {

		this.setLayout(new BorderLayout());

		this.editPanel = new JPanel(new GridLayout(0, 2));

		this.editPanel.add(new JLabel("Nome"));
		this.phytoName = new JTextField();
		this.editPanel.add(this.phytoName);

		this.editPanel.add(new JLabel("Tipo"));
		this.phytoType = new JTextField();
		this.editPanel.add(this.phytoType);

		this.editPanel.add(new JLabel("Giorni"));
		this.days = new JTextField();
		this.editPanel.add(this.days);

		this.editPanel.add(new JLabel("Descrizione"));
		this.phytoDescription = new JTextArea(4, 20);
		this.editPanel.add(this.phytoDescription);

		this.add(this.editPanel);
		this.addbtn = new JButton("Aggiungi");
		this.addbtn.addActionListener(e -> {
			try {
				if (this.phytoName.getText().isEmpty() || this.phytoType.getText().isEmpty()
						|| this.phytoDescription.getText().isEmpty() || this.days.getText().isEmpty()
						|| Integer.parseInt(this.days.getText()) < 0) {
					throw new Exception();
				}
				Controller.getController().addToModel(ModelParam.PHYTOSANITARY, this.phytoName.getText(),
						this.phytoType.getText(), this.phytoDescription.getText(),
						Integer.parseInt(this.days.getText()));
				prev.addToCombo(this.phytoName.getText());
				this.setVisible(false);
			} catch (NumberFormatException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI UN NUMERO", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e2) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ESISTE UN ALTRO PRODOTTO CON LO STESSO NOME!", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e3) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI TUTTI I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.addbtn, BorderLayout.SOUTH);
		this.pack();
	}

}
