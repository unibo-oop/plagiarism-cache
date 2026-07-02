package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import controller.ViewModelView;
import util.Utilities;

/**
 * 
 * Class which implements the view layer for the view use case.
 *
 */
public class ViewForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3362423451078940740L;

	// Table, Name, DAteTime, number Of persons, Telephone, Menu (as notes)
	// The reference to the top level window
	MasterView masterView;

	JButton btnCancel;

	ViewModelView modelView;

	public ViewForm(ViewModelView modelView, MasterView masterView) {
		super(masterView);
		this.modelView = modelView;
		this.modelView.initializeWithSelectedReservation();
		this.reservationId = this.modelView.getReservation().getId();
		this.nameField.setText(this.modelView.getReservation().getName());
		this.nameField.setEditable(false);
		this.emailField.setText(this.modelView.getReservation().getEmail());
		this.emailField.setEditable(false);
		this.phoneField.setText(this.modelView.getReservation().getPhone());
		this.phoneField.setEditable(false);
		this.dateField.setText(Utilities.getFormattedDate(this.modelView.getReservation().getDate()));
		this.dateField.setEditable(false);
		this.tableField.setText(this.modelView.getReservation().getTable().toString());
		this.tableField.setEditable(false);
		this.menuField.setText(this.modelView.getReservation().getMenu());
		this.menuField.setEditable(false);
		this.numberOfPersonsField.setText(this.modelView.getReservation().getNumberOfPersons().toString());
		this.numberOfPersonsField.setEditable(false);
		modelView.bind(this.reservationId.intValue(), this.nameField.getText(), this.emailField.getText(),
				this.phoneField.getText(), this.tableField.getText(), this.dateField.getText(),
				this.numberOfPersonsField.getText(), this.menuField.getText());
		btnCancel = new JButton("Anulla");
		btnCancel.setBounds(50, 350, 100, 30);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelView.toBase();
			}
		});
	}

}
