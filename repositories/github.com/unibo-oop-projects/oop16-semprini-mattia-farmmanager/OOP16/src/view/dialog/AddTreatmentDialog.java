package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import model.Interfaces.PhytosanitaryModel;

public class AddTreatmentDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel editPanel;
	private JComboBox<String> phytosanitary;
	private JButton addPhyto;
	private JPanel dataPanel;
	private JComboBox<Integer> day;
	private JComboBox<Integer> Month;
	private JComboBox<Integer> year;
	private JTextArea description;
	private JButton addBtn;

	public AddTreatmentDialog(String fieldId, String supply, CultivationDetailDialog cd) {
		this.setLayout(new BorderLayout());

		this.editPanel = new JPanel(new GridLayout(0, 3));

		this.editPanel.add(new JLabel("Prodotto"));
		this.phytosanitary = new JComboBox<>();
		this.addToCombo();
		this.editPanel.add(this.phytosanitary);
		this.addPhyto = new JButton("...");
		this.addPhyto.addActionListener(e -> {
			JDialog jd = new AddPhytosanitaryDialog(this);
			jd.setModal(true);
			jd.setVisible(true);
		});
		this.editPanel.add(this.addPhyto);

		this.editPanel.add(new JLabel("Data"));
		this.dataPanel = new JPanel(new GridBagLayout());
		this.day = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });
		this.dataPanel.add(this.day);
		this.Month = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		this.dataPanel.add(this.Month);
		this.year = new JComboBox<>(new Integer[] { 2017, 2018, 2019, 2020, 2021 });
		this.dataPanel.add(this.year);
		this.editPanel.add(this.dataPanel);
		this.editPanel.add(new JLabel(""));

		this.editPanel.add(new JLabel("Descrizione"));
		this.description = new JTextArea(4, 20);
		this.editPanel.add(this.description);

		this.addBtn = new JButton("Aggiungi Trattamento");
		this.addBtn.addActionListener(e -> {
			try {
				if (this.description.getText().isEmpty()) {
					new JOptionPane();
					JOptionPane.showMessageDialog(this, "Completa tutti i campi", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} else {
					Controller.getController().getCultivation(fieldId, supply).addTreatment(
							Controller.getController().getModel()
									.getPhytosanitary((String) this.phytosanitary.getSelectedItem()),
							new GregorianCalendar((Integer) this.year.getSelectedItem(),
									((Integer) this.Month.getSelectedItem()) - 1, (Integer) this.day.getSelectedItem()),
							this.description.getText());
					this.setVisible(false);
				}
			} catch (Exception e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ERRORE NEI DATI INSERITI, RICONTROLLALI", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.editPanel, BorderLayout.CENTER);
		this.add(this.addBtn, BorderLayout.SOUTH);
		this.pack();
	}

	/**
	 * this method is used to add a phytosanitary in this combo
	 * @param ph
	 */
	
	public void addToCombo(String ph) {
		this.phytosanitary.addItem(ph);
	}

	private void addToCombo() {
		for (PhytosanitaryModel ph : Controller.getController().getModel().getPhytosanitaryes()) {
			this.phytosanitary.addItem(ph.getName());
		}
	}

}
