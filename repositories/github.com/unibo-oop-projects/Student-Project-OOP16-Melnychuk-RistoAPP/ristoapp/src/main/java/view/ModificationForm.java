package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import controller.ModificationModelView;
import model.DataContainer;
import util.Utilities;

/**
 * 
 * Class which implements the view layer for the modification use case.
 *
 */
public class ModificationForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -930291287619691904L;

	// Table, Name, DAteTime, number Of persons, Telephone, Menu (as notes)
	// The reference to the top level window
	MasterView masterView;

	JButton btnModify, btnCancel;

	ModificationModelView modelView;

	public ModificationForm(ModificationModelView modelView, MasterView masterView) {
		super(masterView);
		this.modelView = modelView;
		this.modelView.initializeWithSelectedReservation();
		this.reservationId = this.modelView.getReservation().getId();
		this.nameField.setText(this.modelView.getReservation().getName());
		this.emailField.setText(this.modelView.getReservation().getEmail());
		this.phoneField.setText(this.modelView.getReservation().getPhone());
		this.timeField.setText(Utilities.getFormattedTime(this.modelView.getReservation().getDate()));
		this.dateField.setText(Utilities.getFormattedDate(this.modelView.getReservation().getDate()));
		this.tableField.setText(this.modelView.getReservation().getTable().toString());
		this.menuField.setText(this.modelView.getReservation().getMenu());
		this.numberOfPersonsField.setText(this.modelView.getReservation().getNumberOfPersons().toString());
		modelView.bind(this.reservationId.intValue(), this.nameField.getText(), this.emailField.getText(),
				this.phoneField.getText(), this.tableField.getText(), this.dateField.getText(),
				this.numberOfPersonsField.getText(), this.menuField.getText());
		btnModify = new JButton("Modifica");
		btnCancel = new JButton("Anulla");
		btnModify.setBounds(50, 430, 100, 30);
		btnCancel.setBounds(170, 430, 100, 30);
		add(btnModify);
		add(btnCancel);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!val.validate()) {
					Date reservationDate = Utilities.parseDateTime(dateField.getText(), timeField.getText());

					modelView.modifyReservation(reservationId.intValue(), nameField.getText(), emailField.getText(),
							phoneField.getText(), Integer.parseInt(tableField.getText()), reservationDate,
							Integer.parseInt(numberOfPersonsField.getText()), menuField.getText());
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataContainer.remove(DataContainer.SELECTED_RESERVATION_ID);
				modelView.toBase();
			}
		});
	}

}
