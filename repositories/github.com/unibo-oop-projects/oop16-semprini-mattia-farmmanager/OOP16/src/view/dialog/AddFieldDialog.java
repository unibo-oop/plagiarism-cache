package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import controller.ModelParam;

public class AddFieldDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel fieldPanel;
	private JTextField idField;
	private JTextField widthField;
	private JTextField heightField;
	private JButton okbtn;

	public AddFieldDialog(AddCultivationDialog ad) {
		this.setLayout(new BorderLayout());

		this.fieldPanel = new JPanel(new GridLayout(0, 2));

		this.fieldPanel.add(new JLabel("ID Campo"));
		this.idField = new JTextField(20);
		this.fieldPanel.add(this.idField);

		this.fieldPanel.add(new JLabel("Larghezza (in mt)"));
		this.widthField = new JTextField(20);
		this.fieldPanel.add(this.widthField);

		this.fieldPanel.add(new JLabel("Altezza (in mt)"));
		this.heightField = new JTextField(20);
		this.fieldPanel.add(this.heightField);

		this.add(this.fieldPanel, BorderLayout.CENTER);
		this.okbtn = new JButton("Conferma");
		this.okbtn.addActionListener(e -> {
			try {
				if (this.idField.getText().isEmpty() || this.widthField.getText().isEmpty()
						|| this.heightField.getText().isEmpty()) {
					throw new InputMismatchException();
				}
				Controller.getController().addToModel(ModelParam.FIELD, this.idField.getText(),
						Integer.parseInt(this.widthField.getText()), Integer.parseInt(this.heightField.getText()));
				ad.addItemToFieldCombo(this.idField.getText());
				this.setVisible(false);
			} catch (InputMismatchException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "COMPILA TUTTI I CAMPI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e3){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI NUMERI", "ERRORE", JOptionPane.ERROR_MESSAGE);				
			}catch (IllegalArgumentException e2) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ESISTE UN ALTRO CAMPO CON LO STESSO ID", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		;
		this.add(this.okbtn, BorderLayout.SOUTH);
		this.pack();
	}

}
